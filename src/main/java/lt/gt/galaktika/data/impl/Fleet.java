package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IFleet;

public final class Fleet implements IFleet
{
	protected long ID;
	protected String name;
	protected String code;

	public long getID ()
	{
		return ID;
	}
	
	public void setID (long id) {
		this.ID=id;
	}
	
	@Override
	public String getName ()
	{
		return name;
	}

	@Override
	public void setName ( String n )
	{
		name=n;
	}

	@Override
	public String getCode ()
	{
		return code;
	}

	@Override
	public void setCode ( String c )
	{
		code=c;
	}

	@Override
	public String toString ()
	{
		return  "Fleet "+ID + " "+name;
	}

	@Override
	public String getFillInfo ()
	{
		return "fleet";
	}
	
	

}
