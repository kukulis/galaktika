package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IPlace;
import lt.gt.galaktika.data.ISpaceTraveler;

public class SpaceTraveler implements ISpaceTraveler
{
	private double speed;
	private IPlace place;
	
	public double getSpeed ()
	{
		return speed;
	}
	public void setSpeed ( double speed )
	{
		this.speed = speed;
	}
	public IPlace getPlace ()
	{
		return place;
	}
	public void setPlace ( IPlace place )
	{
		this.place = place;
	}
	
	
}
