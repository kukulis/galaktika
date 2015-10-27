package lt.gt.galaktika.data;

import java.util.Collection;

public interface IPlanetsSpace
{
	public Collection<IPlanet> getPlanets();
	public IPlanet getPlanet(long planetId );

}
