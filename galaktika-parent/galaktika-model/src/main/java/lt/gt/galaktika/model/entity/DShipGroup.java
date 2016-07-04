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
	
	public DShipGroup () {
		
	}
	public DShipGroup ( DShip ship ) {
		this.ship=ship;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipGroupId;
	
	@ManyToOne
	@JoinColumn(name = "fleetId", nullable = true)
	private DFleet fleet;
//	private long shipId;
	
	@ManyToOne
	@JoinColumn(name = "shipId", nullable = false)
	private DShip ship;
	

	private int count=1;
	
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
}
