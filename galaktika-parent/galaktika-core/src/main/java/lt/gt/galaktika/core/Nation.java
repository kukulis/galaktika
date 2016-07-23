package lt.gt.galaktika.core;

import java.io.Serializable;

public class Nation implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -253086463738009384L;
	private long nationId;
	private String nationName;
	
	public Nation()
	{
	}

	public Nation(String nationName)
	{
		super();
		this.nationName = nationName;
	}

	public Nation(long nationId, String nationName)
	{
		this.nationId = nationId;
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
	public String toString ()
	{
		return "["+nationId+":"+nationName+"]";
	}

	@Override
	public int hashCode ()
	{
		return Long.hashCode( nationId );
	}

	@Override
	public boolean equals ( Object obj )
	{
		if ( obj instanceof Nation ) {
			Nation n =(Nation) obj;
			return n.getNationId() == nationId;
		}
		else return false;
	}
}
