package lt.gt.galaktika.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
//	private long nationId;
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "nationId", nullable = true)
	private DNation nation;
	
	// @Column ( nullable=true)
	private long turnId;
	
	private boolean deleted;
	
	@OneToMany (fetch=FetchType.LAZY )
	@JoinColumn(name="fleetId")
	private List<DShipGroup> shipGroups=new ArrayList<>();
	
	public DFleet()
	{
	}
	public DFleet(String name)
	{
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
//	public long getNationId ()
//	{
//		return nationId;
//	}
//	public void setNationId ( long nationId )
//	{
//		this.nationId = nationId;
//	}
	public boolean isDeleted ()
	{
		return deleted;
	}
	public void setDeleted ( boolean deleted )
	{
		this.deleted = deleted;
	}
	public DNation getNation ()
	{
		return nation;
	}
	public void setNation ( DNation nation )
	{
		this.nation = nation;
	}
	public List<DShipGroup> getShipGroups ()
	{
		return shipGroups;
	}
	public void setShipGroups ( List<DShipGroup> shipGroups )
	{
		this.shipGroups = shipGroups;
	}
}
