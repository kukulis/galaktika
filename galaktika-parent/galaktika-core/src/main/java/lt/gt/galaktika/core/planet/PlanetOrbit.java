package lt.gt.galaktika.core.planet;

import java.util.ArrayList;
import java.util.List;

import lt.gt.galaktika.core.Fleet;
import lt.gt.galaktika.core.Nation;

public class PlanetOrbit
{
	private List<Fleet> fleets = new ArrayList<>();

	public List<Fleet> getFleets ()
	{
		return fleets;
	}

	public void setFleets ( List<Fleet> fleets )
	{
		this.fleets = fleets;
	}
	
	public Fleet findNationFleet(Nation n) {
		for ( Fleet f : fleets )
			if ( n.equals(f.getOwner()))
					return f;
		return null;
	}
}
