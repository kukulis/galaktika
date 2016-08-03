package lt.gt.galaktika.model.entity.turn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lt.gt.galaktika.model.entity.noturn.DShip;

@Entity
@Table(name = "ship_groups")
public class DShipGroup
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipGroupId;

	@ManyToOne
	@JoinColumn(name = "shipId", nullable = false)
	private DShip ship;
	private long fleetId;
	private long turnId;

	private int shipsCount = 1;
	
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

	public int getShipsCount ()
	{
		return shipsCount;
	}

	public void setShipsCount ( int count )
	{
		this.shipsCount = count;
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
		long myFleetId = getFleetId();
		long myShipId = getShipId( this );
		
		return Long.hashCode( (myFleetId >>> 31) + myShipId + (turnId >>> 15) );
	}

	@Override
	public boolean equals ( Object obj )
	{
		if (!  (obj instanceof DShipGroup) )
			return false;
		DShipGroup given = (DShipGroup) obj;
		long myFleetId = getFleetId( );
		long myShipId = getShipId( this );
		long myTurnId = this.getTurnId();
		
		long givenFleetId = given.getFleetId();
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
	
	

	public long getFleetId() {
		return fleetId;
	}

	public void setFleetId(long fleetId) {
		this.fleetId = fleetId;
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
		this.shipsCount = source.getShipsCount();
	}
}
