package lt.gt.galaktika.data.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lt.gt.galaktika.data.ILeader;
import lt.gt.galaktika.data.IPlanet;
import lt.gt.galaktika.data.IRichGalaxy;

/**
 * Galaxy with all the data, but might be loaded separately.
 * 
 * @author giedrius
 *
 */
public class RichGalaxy implements IRichGalaxy
{
	private long id;
	private Map <Long,ILeader> leadersMap=new HashMap<Long,ILeader>();
	private Map <Long,IPlanet> planetsMap=new HashMap<Long,IPlanet> ();
	
	private RichGalaxy( List <ILeader> leaders, List<IPlanet> planets ) {
	}
	
	// Gal būt šito paterno visai nereikia, bet pabandymui čia :)
	public static class RichGalaxyBuilder {
		private List <ILeader> leaders;
		private List <IPlanet> planets;
		
		private RichGalaxyBuilder () { }
		
		private static class RichGalaxyBuilderHelper {
			static RichGalaxyBuilder INSTANCE=new RichGalaxyBuilder();
		}
		
		public static RichGalaxyBuilder getInstance() {
			return RichGalaxyBuilderHelper.INSTANCE;
		}
		
		public RichGalaxyBuilder setLeaders ( List <ILeader> leaders ) {
			this.leaders = leaders;
			return this;
		}
		public RichGalaxyBuilder setPlanets ( List<IPlanet> planets ) {
			this.planets = planets;
			return this;
		}
		
		public RichGalaxy build () {
			return new RichGalaxy ( leaders, planets );
		}
	}

	@Override
	public Collection<ILeader> getLeaders ()
	{
		return leadersMap.values();
	}

	@Override
	public ILeader getLeader ( long leaderId )
	{
		return leadersMap.get( leaderId );
	}

	@Override
	public Collection<IPlanet> getPlanets ()
	{
		return planetsMap.values();
	}

	@Override
	public IPlanet getPlanet ( long planetId )
	{
		return planetsMap.get( planetId);
	}

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
