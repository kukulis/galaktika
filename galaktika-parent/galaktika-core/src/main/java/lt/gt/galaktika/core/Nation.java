package lt.gt.galaktika.core;

public class Nation
{
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
	
}
