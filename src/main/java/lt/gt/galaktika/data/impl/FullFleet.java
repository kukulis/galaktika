package lt.gt.galaktika.data.impl;

import java.util.List;

import lt.gt.galaktika.data.IFullFleet;
import lt.gt.galaktika.data.IPlace;
import lt.gt.galaktika.data.IShipGroup;

/**
 * 
 */
public final class FullFleet implements IFullFleet
{
	private Fleet fleet;
	private SpaceTraveler spaceTraveler;
	private ShipContainer shipContainer;
	
	public FullFleet ( Fleet f, SpaceTraveler t, ShipContainer c ) {
		fleet = f;
		spaceTraveler = t;
		shipContainer = c;
	}
	
	// TODO write other constructors
	
	public long getID ()
	{
		return fleet.getID();
	}
	public String getName ()
	{
		return fleet.getName();
	}
	public void setID ( long id )
	{
		fleet.setID(id);
	}
	public void setName ( String n )
	{
		fleet.setName(n);
	}
	public String getCode ()
	{
		return fleet.getCode();
	}
	public void setCode ( String c )
	{
		fleet.setCode(c);
	}
	public double getSpeed ()
	{
		return spaceTraveler.getSpeed();
	}
	public void setSpeed ( double speed )
	{
		spaceTraveler.setSpeed(speed);
	}
	public IPlace getPlace ()
	{
		return spaceTraveler.getPlace();
	}
	public void setPlace ( IPlace p )
	{
		spaceTraveler.setPlace(p);
	}
	public List<IShipGroup> getShipGroups ()
	{
		return shipContainer.getShipGroups();
	}

	// wrapees getters and setters
	
	public Fleet getFleet ()
	{
		return fleet;
	}

	public void setFleet ( Fleet fleet )
	{
		this.fleet = fleet;
	}

	public SpaceTraveler getSpaceTraveler ()
	{
		return spaceTraveler;
	}

	public void setSpaceTraveler ( SpaceTraveler spaceTraveler )
	{
		this.spaceTraveler = spaceTraveler;
	}

	public ShipContainer getShipContainer ()
	{
		return shipContainer;
	}

	public void setShipContainer ( ShipContainer shipContainer )
	{
		this.shipContainer = shipContainer;
	}

	@Override
	public String getFillInfo ()
	{
		StringBuffer fillInfo = new StringBuffer();
		if ( fleet != null ) 
			fillInfo.append ( "fleet");
		else 
			fillInfo.append( "--" );
		
		if ( shipContainer != null )
			fillInfo.append( " container" );
		else 
			fillInfo.append( " --" );

		if ( spaceTraveler != null )
			fillInfo.append( " traveler" );
		else 
			fillInfo.append( " --" );
		
		
		return fillInfo.toString();
	}
	// **************************************************
	// **************************************************
	// **************************************************
	// **************************************************
	
	/**
	 * TODO use counter, which will be updated in "add" and "remove" methods.
	 * @return
	 */
	public int calculateShips () {
		int count = 0;
		for ( IShipGroup group : shipContainer.getShipGroups() ) {
			count += group.getAmount();
		}
		return count;
	}
	
	public void resetShotsAmount () {
		for ( IShipGroup group : shipContainer.getShipGroups() ) {
			group.setShotedShips( 0 );
		}
	}
	
	/**
	 * TODO use counters for performance
	 * @return
	 */
	public int calculateShots() {
		int shots = 0;
		for ( IShipGroup group : shipContainer.getShipGroups() ) {
			shots += group.getShotedShips();
		}
		
		return shots;
	}
	
	public int calculateShipsWithGuns () {
		int count = 0;
		for ( IShipGroup group : shipContainer.getShipGroups() ) {
			if ( group.getShip().getGuns() > 0 ) {
				count += group.getAmount();
			}
		}
		return count;
	}
	
	public int calculateAbleShotShipCount () {
		int count = 0;
		for ( IShipGroup group : shipContainer.getShipGroups() ) {
			count += group.getAbleShotAmount();
		}
		return count;
	}
	
	public IShipGroup selectAttackerGroup (int shipNumber) {
		int i =0;
		
		while (  shipContainer.getShipGroups().size() > i && (shipNumber > 0 || shipContainer.getShipGroups().get(i).getAbleShotAmount() == 0 )  ) {
			IShipGroup group = shipContainer.getShipGroups().get( i );
			if ( group.getShip().getGuns() > 0 ) {
				shipNumber = shipNumber - group.getAbleShotAmount();
			}
			i++;
		}
		if ( i < shipContainer.getShipGroups().size() )
			return shipContainer.getShipGroups().get(i);
		else
			return null;
		
	}
	
	public IShipGroup selectAnyGroup (int shipNumber) {
		int i =0;
		
		while (  shipContainer.getShipGroups().size() > i && (shipNumber > 0 || shipContainer.getShipGroups().get(i).getAmount() == 0 )  ) {
			IShipGroup group = shipContainer.getShipGroups().get( i );
			shipNumber = shipNumber - group.getAmount();
			i++;
		}
		if ( i < shipContainer.getShipGroups().size() )
			return shipContainer.getShipGroups().get(i);
		else
			return null;
		
	}

	@Override
	public void addShipGroup ( IShipGroup group )
	{
		shipContainer.addShipGroup( group );
	}
	
	
}
