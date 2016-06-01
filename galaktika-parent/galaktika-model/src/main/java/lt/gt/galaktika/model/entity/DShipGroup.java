package lt.gt.galaktika.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ship_groups")
public class DShipGroup
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long shipGroupId;
	
	private long fleetId;
	private long shipId;

	private int count=1;
	
	public long getShipGroupId ()
	{
		return shipGroupId;
	}
	public void setShipGroupId ( long shipGroupId )
	{
		this.shipGroupId = shipGroupId;
	}
	public long getFleetId ()
	{
		return fleetId;
	}
	public void setFleetId ( long fleetId )
	{
		this.fleetId = fleetId;
	}
	public long getShipId ()
	{
		return shipId;
	}
	public void setShipId ( long shipId )
	{
		this.shipId = shipId;
	}
	public int getCount ()
	{
		return count;
	}
	public void setCount ( int count )
	{
		this.count = count;
	}
}
