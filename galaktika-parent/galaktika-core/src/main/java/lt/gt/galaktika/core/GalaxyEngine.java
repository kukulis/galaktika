package lt.gt.galaktika.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lt.gt.galaktika.core.battle.BattleParticipants;
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
			// double speed = fleet.calculateSpeed();
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
				return fleet.getFlightCommand().getDestination();
		}
		return null;

	}

	public List<BattleParticipants> findBattles ( List<Fleet> fleets, Set<Aggreement> aggreements )
	{

		// TODO
		return null;
	}

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
			sourceFleets.stream().filter(fleetB -> !analyzedFleetsPairs.contains(new TwoFleets(fleetA, fleetB)) // avoid
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

	public List<PlanetData> findBombings ( List<Fleet> fleets, List<PlanetData> planets, Set<Aggreement> aggreeements )
	{
		// TODO
		return null;
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
