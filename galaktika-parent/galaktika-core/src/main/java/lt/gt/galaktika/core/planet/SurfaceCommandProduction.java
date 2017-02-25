package lt.gt.galaktika.core.planet;

import lt.gt.galaktika.core.Technologies;

public class SurfaceCommandProduction implements SurfaceCommand
{
	private ShipDesign shipDesign;
	private Technologies technologies;
	private int maxShips=1; 
	
	@Override
	public SurfaceActivities getActivityType ()
	{
		return SurfaceActivities.PRODUCTION;
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

	public int getMaxShips ()
	{
		return maxShips;
	}

	public void setMaxShips ( int maxShips )
	{
		this.maxShips = maxShips;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurfaceCommandProduction other = (SurfaceCommandProduction) obj;
		if (maxShips != other.maxShips)
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
