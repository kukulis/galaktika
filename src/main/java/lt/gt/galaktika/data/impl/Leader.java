package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.ILeader;

public class Leader implements ILeader
{
	private long id;
	private String name;

	@Override
	public long getID ()
	{
		return id;
	}

	@Override
	public void setID ( long id )
	{
		this.id = id;
	}

	public String getName ()
	{
		return name;
	}

	public void setName ( String name )
	{
		this.name = name;
	}
}
