package lt.gt.galaktika.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lt.gt.galaktika.core.battle.BattleParticipants;
import lt.gt.galaktika.core.planet.Planet;
import lt.gt.galaktika.core.planet.PlanetData;
import lt.gt.math.PlaneVector;

public class GalaxyEngine
{
	public GalaxyLocation calculateMovement ( Fleet fleet, Galaxy galaxy )
	{
		return calculateMovement(fleet, fleet.calculateSpeed(), galaxy);
	}

	public GalaxyLocation calculateMovement ( Fleet fleet, double distance, Galaxy galaxy )
	{
		if (fleet.getFlightCommand() != null)
		{
			// 1 calculate movement vector
			PlaneVector movementVector = galaxy.getShortestVector(fleet.getGalaxyLocation(),
					fleet.getFlightCommand().getDestination());
			if (movementVector.mod() > distance)
			{
				// calculate intermediate point
				double part = distance / movementVector.mod();
				// calculate shorter vector
				PlaneVector thisTurnVector = new PlaneVector(movementVector.getX() * part,
						movementVector.getY() * part);

				return galaxy.normalize(fleet.getGalaxyLocation().getX() + thisTurnVector.getX(),
						fleet.getGalaxyLocation().getY() + thisTurnVector.getY());

			}
			else
				return fleet.getFlightCommand().getDestination(); // move to
																	// destination
		}
		return fleet.getGalaxyLocation(); // stay at the same location

	}

	/**
	 * Find battles in planets. Assume all the fleets already moved.
	 * 
	 * @param fleets
	 * @param aggreementsObject
	 * @param galaxy
	 * @return
	 */
	public List<BattleParticipants> findPlanetBattles ( List<Fleet> fleets, AggreementsObject aggreementsObject,
			Galaxy galaxy )
	{
		System.out.println("fleets.size=" + fleets.size());
		// collect fleets in groups by locations
		Map<GalaxyLocation, List<Fleet>> locationsMap = fleets.stream()
				.collect(Collectors.groupingBy(Fleet::getGalaxyLocation));

		System.out.println("locationsMap.size=" + locationsMap.size());

		List<BattleParticipants> result = new ArrayList<>();

		// as many battles, as there are fleets in war one by one
		locationsMap.forEach( ( l, localFleetsList ) -> {
			System.out.println("Analyzing location " + l);
			// battle is each with each
			for (int i = 0; i < localFleetsList.size() - 1; i++)
				for (int j = i + 1; j < localFleetsList.size(); j++)
					// battle between i and j fleet
					if (aggreementsObject.isInWar(localFleetsList.get(i).getOwner(), localFleetsList.get(j).getOwner()))
						result.add(new BattleParticipants(localFleetsList.get(i), localFleetsList.get(j), l));
		});

		return result;
	}

	/**
	 * Splits to subsets, which fleets are not in war with other fleet in the
	 * same set.
	 * 
	 * @param fleetsSource
	 * @return
	 */
	public List<List<Fleet>> splitToPeacefulSubsets ( List<Fleet> fleetsSource, AggreementsObject aggreementsObject )
	{
		List<List<Fleet>> result = new ArrayList<>();
		for (Fleet fleet : fleetsSource)
		{
			List<Fleet> candidate = findPeacefulSublist(result, fleet, aggreementsObject);
			if (candidate == null)
			{
				candidate = new ArrayList<>();
				result.add(candidate);
			}
			candidate.add(fleet);
		}
		return result;
	}

	public List<Fleet> findPeacefulSublist ( List<List<Fleet>> fleetsLists, Fleet newFleet,
			AggreementsObject aggreementsObject )
	{
		for (List<Fleet> fleetsList : fleetsLists)
			if (!aggreementsObject.isInWar(fleetsList, newFleet))
				return fleetsList;
		return null;
	}

	/**
	 * Find battles in space. Assume fleets didn't move yet.
	 * 
	 * @param fleets
	 * @param aggreementsObject
	 * @param galaxy
	 * @return
	 */
	public List<BattleParticipants> findSpaceBattles ( List<Fleet> fleets, AggreementsObject aggreementsObject,
			Galaxy galaxy )
	{

		Set<TwoFleets> analyzedFleetsPairs = new HashSet<>(); // to avoid
																// duplicates
		Map<GalaxyLocation, List<Fleet>> movementSourceMap = fleets.stream().filter(f -> f.getFlightCommand() != null)
				.collect(Collectors.groupingBy(Fleet::getFlightSource));
		// the result
		List<BattleParticipants> battleParticipants = new ArrayList<>();
		fleets.stream().filter(f -> f.getFlightCommand() != null).forEach(fleetA -> {
			List<Fleet> sourceFleets = movementSourceMap.get(fleetA.getFlightCommand().getDestination());
			sourceFleets.stream()
					.filter(fleetB -> !analyzedFleetsPairs.contains(new TwoFleets(fleetA, fleetB)) // avoid
																									// duplicates
							&& fleetA.getFlightSource().equals(fleetB.getFlightDestination())
							&& aggreementsObject.isInWar(fleetA.getOwner(), fleetB.getOwner())
							&& willMeet(fleetA, fleetB, galaxy))
					.forEach(fleetB -> {
						battleParticipants.add(new BattleParticipants(fleetA, fleetB,
								calculateBattleLocation(fleetA, fleetB, galaxy)));
						analyzedFleetsPairs.add(new TwoFleets(fleetA, fleetB)); // avoid
																				// duplicates
					});
		});
		return battleParticipants;
	}

	public boolean willMeet ( Fleet fleetA, Fleet fleetB, Galaxy galaxy )
	{
		PlaneVector distanceVector = galaxy.getShortestVector(fleetA.getGalaxyLocation(), fleetB.getGalaxyLocation());
		return fleetA.calculateSpeed() + fleetB.calculateSpeed() >= distanceVector.mod();
	}

	public GalaxyLocation calculateBattleLocation ( Fleet fleetA, Fleet fleetB, Galaxy galaxy )
	{
		PlaneVector distanceVector = galaxy.getShortestVector(fleetA.getGalaxyLocation(), fleetB.getGalaxyLocation());

		double speedA = fleetA.calculateSpeed();
		double speedB = fleetB.calculateSpeed();
		double distance = distanceVector.mod();

		double totalSpeed = speedA + speedB;
		double movementA = speedA * (distance / totalSpeed);

		return calculateMovement(fleetA, movementA, galaxy);
	}

	
	/**
	 * Gets fleets which are in war with the planet, they currently are staying in.
	 * @param fleets
	 * @param planets
	 * @param aggreementsObject
	 * @return
	 */
	public List<Fleet> findBombings ( List<Fleet> fleets, List<PlanetData> planets,
			AggreementsObject aggreementsObject )
	{
		Map<Planet, PlanetData> planetsMap = new HashMap<Planet, PlanetData>();
		planets.forEach(p -> planetsMap.put(p.getPlanet(), p));

		return fleets.stream().filter(fleet -> isInWar(fleet, fleet.getGalaxyLocation(), planetsMap, aggreementsObject))
				.collect(Collectors.toList());
	}

	/**
	 * Is the fleet in war with the planet it is currently staying in.
	 * @param fleet
	 * @param location
	 * @param planetsMap
	 * @param aggreementsObject
	 * @return
	 */
	private boolean isInWar ( Fleet fleet, GalaxyLocation location, Map<Planet, PlanetData> planetsMap,
			AggreementsObject aggreementsObject )
	{
		if (!(location instanceof Planet))
			return false;
		PlanetData planetData = planetsMap.get((Planet) location);
		return aggreementsObject.isInWar(fleet.getOwner(), planetData.getSurface().getNation());
	}

	public static class TwoFleets
	{
		private Fleet fleet1, fleet2;

		public TwoFleets(Fleet fleet1, Fleet fleet2)
		{
			super();
			this.fleet1 = fleet1;
			this.fleet2 = fleet2;
		}

		public Fleet getFleet1 ()
		{
			return fleet1;
		}

		public void setFleet1 ( Fleet fleet1 )
		{
			this.fleet1 = fleet1;
		}

		public Fleet getFleet2 ()
		{
			return fleet2;
		}

		public void setFleet2 ( Fleet fleet2 )
		{
			this.fleet2 = fleet2;
		}

		@Override
		public int hashCode ()
		{
			// symetrical
			return fleet1.hashCode() ^ fleet2.hashCode();
		}

		@Override
		public boolean equals ( Object obj )
		{
			if (obj instanceof TwoFleets)
			{
				TwoFleets tf = (TwoFleets) obj;
				return fleet1.equals(tf.getFleet1()) && fleet2.equals(tf.getFleet2())
						|| fleet2.equals(tf.getFleet1()) && fleet1.equals(tf.getFleet2());
			}
			else
				return false;
		}

	}

}
