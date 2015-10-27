package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IGalaxy;

public class Galaxy implements IGalaxy
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
