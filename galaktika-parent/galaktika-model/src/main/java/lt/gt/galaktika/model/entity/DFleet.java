package lt.gt.galaktika.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size; 


@Entity
@Table(name = "fleets")
public class DFleet
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fleetId;
	
	@Size(max = 32)
	private String name; // optional ?
	
	// relations
	private long nationId;
	private long turnId;
	
	public long getFleetId ()
	{
		return fleetId;
	}
	public void setFleetId ( long fleetId )
	{
		this.fleetId = fleetId;
	}
	public String getName ()
	{
		return name;
	}
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public long getTurnId ()
	{
		return turnId;
	}
	public void setTurnId ( long turnId )
	{
		this.turnId = turnId;
	}
	public long getNationId ()
	{
		return nationId;
	}
	public void setNationId ( long nationId )
	{
		this.nationId = nationId;
	}
	
	
}
