package lt.gt.galaktika.core;

import java.util.ArrayList;
import java.util.List;

public class Fleet
{
	private long fleetId;

	private String name;

	private List<ShipGroup> shipGroups = new ArrayList<>();
	private GalaxyLocation galaxyLocation;

	private FlightCommand flightCommand;

	private Nation owner = new Nation();

	public Fleet()
	{
	}

	public Fleet(String name)
	{
		this.name = name;
	}

	public Fleet(long fleetId, String name)
	{
		super();
		this.fleetId = fleetId;
		this.name = name;
	}

	public long getFleetId ()
	{
		return fleetId;
	}

	public void setFleetId ( long fleetId )
	{
		this.fleetId = fleetId;
	}

	public List<ShipGroup> getShipGroups ()
	{
		return shipGroups;
	}

	public void setShipGroups ( List<ShipGroup> shipGroups )
	{
		this.shipGroups = shipGroups;
	}

	public GalaxyLocation getGalaxyLocation ()
	{
		return galaxyLocation;
	}

	public void setGalaxyLocation ( GalaxyLocation galaxyLocation )
	{
		this.galaxyLocation = galaxyLocation;
	}

	public String getName ()
	{
		return name;
	}

	public void setName ( String name )
	{
		this.name = name;
	}

	public void addShipGroup ( ShipGroup shipGroup )
	{
		shipGroup.setFleet(this);
		this.shipGroups.add(shipGroup);
	}

	public int calculateShips ()
	{
		return shipGroups.stream().mapToInt(g -> g.getCount()).sum();
	}

	public int calculateShipsWithGuns ()
	{
		return shipGroups.stream().filter(g -> g.getShip().getGuns() > 0).mapToInt(g -> g.getCount()).sum();
	}

	public int calculateAbleShotShipCount ()
	{
		return shipGroups.stream().mapToInt(g -> g.getAbleShotAmount()).sum();
	}

	public int calculateShots ()
	{
		return shipGroups.stream().mapToInt(g -> g.getShotedCount()).sum();
	}

	public void resetShotsAmount ()
	{
		shipGroups.stream().forEach(g -> g.setShotedCount(0));
	}

	/**
	 * 
	 * @param shipNumber
	 *            global ship number in the fleet.
	 * @return
	 */
	public ShipGroup selectAnyGroup ( int shipNumber )
	{
		int i = 0;

		while (shipGroups.size() > i && (shipNumber > 0 || shipGroups.get(i).getCount() == 0))
		{
			ShipGroup group = shipGroups.get(i);
			shipNumber = shipNumber - group.getCount();
			i++;
		}
		if (i < shipGroups.size())
			return shipGroups.get(i);
		else
			return null;

	}

	// TODO reuse with selectAnyGroup
	public ShipGroup selectAttackerGroup ( int shipNumber )
	{
		int i = 0;
		while (shipGroups.size() > i && (shipNumber > 0 || shipGroups.get(i).getAbleShotAmount() == 0))
		{
			ShipGroup group = shipGroups.get(i);
			if (group.getShip().getGuns() > 0)
			{
				shipNumber = shipNumber - group.getAbleShotAmount();
			}
			i++;
		}
		if (i < shipGroups.size())
			return shipGroups.get(i);
		else
			return null;
	}

	@Override
	public String toString ()
	{
		return fleetId + " " + name;
	}

	public Nation getOwner ()
	{
		return owner;
	}

	public void setOwner ( Nation owner )
	{
		this.owner = owner;
	}

	// public GalaxyLocation getTargetLocation ()
	// {
	// return targetLocation;
	// }
	//
	// public void setTargetLocation ( GalaxyLocation targetLocation )
	// {
	// this.targetLocation = targetLocation;
	// }

	public double calculateSpeed ()
	{
		return shipGroups.stream().map(g -> g.getShip().getSpeed()).min(Double::compare).get();
	}

	public FlightCommand getFlightCommand ()
	{
		return flightCommand;
	}

	public void setFlightCommand ( FlightCommand flightCommand )
	{
		this.flightCommand = flightCommand;
	}

	public GalaxyLocation getFlightSource ()
	{
		if (flightCommand != null)
			return flightCommand.getSource();
		else
			return null;
	}

	public GalaxyLocation getFlightDestination ()
	{
		if (flightCommand != null)
			return flightCommand.getDestination();
		else
			return null;
	}

	@Override
	public int hashCode ()
	{
		return Long.hashCode(fleetId);
	}

	@Override
	public boolean equals ( Object obj )
	{
		if (obj instanceof Fleet)
		{
			Fleet f = (Fleet) obj;
			return f.getFleetId() == fleetId;
		}
		else
			return false;
	}

}
