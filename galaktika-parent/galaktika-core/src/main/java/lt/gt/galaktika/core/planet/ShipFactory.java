package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.Ship;
import lt.gt.galaktika.core.Technologies;

/**
 * This is business logic factory.
 * @author Giedrius Tumelis
 *
 */
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
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		long temp;
//		temp = Double.doubleToLongBits(donePart);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		result = prime * result + ((ship == null) ? 0 : ship.hashCode());
//		result = prime * result + ((shipDesign == null) ? 0 : shipDesign.hashCode());
//		result = prime * result + ((technologies == null) ? 0 : technologies.hashCode());
//		return result;
//	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipFactory other = (ShipFactory) obj;
		if (Double.doubleToLongBits(donePart) != Double.doubleToLongBits(other.donePart))
			return false;
		if (ship == null) {
			if (other.ship != null)
				return false;
		} else if (!ship.equals(other.ship))
			return false;
		if (shipDesign == null) {
			if (other.shipDesign != null)
				return false;
		} else if (!shipDesign.equals(other.shipDesign))
			return false;
		if (technologies == null) {
			if (other.technologies != null)
				return false;
		} else if (!technologies.equals(other.technologies))
			return false;
		return true;
	}
}
