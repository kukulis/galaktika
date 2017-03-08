package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "nation")
public class DNation
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Access(AccessType.PROPERTY)
	long nationId;
	
	String nationName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	User user;
	@ManyToOne(fetch = FetchType.LAZY)
	DGalaxy galaxy;
	
	
	
	public DNation(long nationId, String nationName) {
		this.nationId = nationId;
		this.nationName = nationName;
	}

	public DNation() {
	}

	public DNation(String nationName) {
		this.nationName = nationName;
	}
	
	public long getNationId ()
	{
		return nationId;
	}
	public void setNationId ( long nationId )
	{
		this.nationId = nationId;
	}
	
	public String getNationName ()
	{
		return nationName;
	}
	public void setNationName ( String nationName )
	{
		this.nationName = nationName;
	}

	@Override
	public boolean equals(Object obj) {
		if ( obj instanceof DNation ) {
			DNation nation = (DNation) obj;
			return nationId==nation.nationId;
		}
		else
			return false;
	}

	@Override
	public String toString() {
		return "DNation [nationId=" + nationId + ", nationName=" + nationName + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DGalaxy getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(DGalaxy galaxy) {
		this.galaxy = galaxy;
	}
}
