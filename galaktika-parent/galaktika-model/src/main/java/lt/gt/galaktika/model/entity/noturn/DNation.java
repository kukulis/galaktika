package lt.gt.galaktika.model.entity.noturn;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	long userId;
	
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
	
	public long getUserId ()
	{
		return userId;
	}
	public void setUserId ( long userId )
	{
		this.userId = userId;
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
	public String toString() {
		return "DNation["+nationId+", "+nationName+"]";
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
	
	
}
