package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.Technologies;

public class ShipFactory
{
	private Ship ship;
	private ShipDesign shipDesign;
	private Technologies technologies;
	private double donePart;
	public Ship getShip ()
	{
		return ship;
	}
	public void setShip ( Ship ship )
	{
		this.ship = ship;
	}
	public double getDonePart ()
	{
		return donePart;
	}
	public void setDonePart ( double donePart )
	{
		this.donePart = donePart;
	}
	public ShipDesign getShipDesign ()
	{
		return shipDesign;
	}
	public void setShipDesign ( ShipDesign shipDesign )
	{
		this.shipDesign = shipDesign;
	}
	public Technologies getTechnologies ()
	{
		return technologies;
	}
	public void setTechnologies ( Technologies technologies )
	{
		this.technologies = technologies;
	}
	
}
