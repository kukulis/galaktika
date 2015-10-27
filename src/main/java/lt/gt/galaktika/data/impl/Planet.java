package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IPlanet;

public class Planet implements IPlanet
{
	private long id;

	@Override
	public long getID ()
	{
		return id;
	}

	@Override
	public void setID ( long id )
	{
		this.id=id;
	}

}
