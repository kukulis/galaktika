package lt.gt.galaktika.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ship_groups")
public class DShipGroup
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipGroupId;

	@ManyToOne
	@JoinColumn(name = "fleetId", nullable = true)
	private DFleet fleet;
	// private long shipId;

	@ManyToOne
	@JoinColumn(name = "shipId", nullable = false)
	private DShip ship;
	
	private long turnId;

	private int count = 1;
	
	public DShipGroup()
	{

	}

	public DShipGroup(DShip ship)
	{
		this.ship = ship;
	}
	
	public DShipGroup(long shipGroupId, DShip ship)
	{
		super();
		this.shipGroupId = shipGroupId;
		this.ship = ship;
	}

	public long getShipGroupId ()
	{
		return shipGroupId;
	}

	public void setShipGroupId ( long shipGroupId )
	{
		this.shipGroupId = shipGroupId;
	}

	public int getCount ()
	{
		return count;
	}

	public void setCount ( int count )
	{
		this.count = count;
	}

	public DFleet getFleet ()
	{
		return fleet;
	}

	public void setFleet ( DFleet fleet )
	{
		this.fleet = fleet;
	}

	public DShip getShip ()
	{
		return ship;
	}

	public void setShip ( DShip ship )
	{
		this.ship = ship;
	}

	@Override
	public int hashCode ()
	{
		long myFleetId = getFleetId( this );
		long myShipId = getShipId( this );
		
		return Long.hashCode( (myFleetId >>> 31) + myShipId + (turnId >>> 15) );
	}

	@Override
	public boolean equals ( Object obj )
	{
		if (!  (obj instanceof DShipGroup) )
			return false;
		DShipGroup given = (DShipGroup) obj;
		long myFleetId = getFleetId( this );
		long myShipId = getShipId( this );
		long myTurnId = this.getTurnId();
		
		long givenFleetId = getFleetId( given );
		long givenShipId = getShipId( given );
		long givenTurnId = given.getTurnId();
		
		
		return myFleetId == givenFleetId && myShipId == givenShipId && myTurnId == givenTurnId;
	}

	public static long getShipId ( DShipGroup group )
	{
		if (group.getShip() != null)
			return group.getShip().getId();
		return 0;
	}

	public static long getFleetId ( DShipGroup group )
	{
		if (group.getFleet() != null)
			return group.getFleet().getFleetId();
		return 0;
	}

	public long getTurnId ()
	{
		return turnId;
	}

	public void setTurnId ( long turnId )
	{
		this.turnId = turnId;
	}
	
	public void updateValues ( DShipGroup source ) {
		this.count = source.getCount();
	}
}
