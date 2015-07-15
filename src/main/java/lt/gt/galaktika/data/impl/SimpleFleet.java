package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IFleet;

public class SimpleFleet implements IFleet
{
	protected long ID;

	public long getID ()
	{
		return ID;
	}
	
	public void setID (long id) {
		this.ID=id;
	}

	@Override
	public String toString ()
	{
		return  "Fleet "+ID;
	}

}
