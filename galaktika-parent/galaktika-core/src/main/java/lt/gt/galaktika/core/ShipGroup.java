package lt.gt.galaktika.core;

public class ShipGroup
{
	private Fleet fleet;
	private Ship ship;
	private int count=1;
	
	// TODO relations
	private long turnId;
	private long userId;
	
	/**
	 * How many ships did a shot.
	 */
	private int shotedCount=0;
	
	public ShipGroup ( Ship ship ) {
		this.ship=ship;
	}
	public Fleet getFleet ()
	{
		return fleet;
	}
	public void setFleet ( Fleet fleet )
	{
		this.fleet = fleet;
	}
	public Ship getShip ()
	{
		return ship;
	}
	public void setShip ( Ship ship )
	{
		this.ship = ship;
	}
	public int getCount ()
	{
		return count;
	}
	public void setCount ( int count )
	{
		this.count = count;
	}
	public int getShotedCount ()
	{
		return shotedCount;
	}
	public void setShotedCount ( int shotedCount )
	{
		this.shotedCount = shotedCount;
	}
	
	public int getAbleShotAmount () {
		if ( getShip().getGuns() > 0 ) {
			int ableShot = count - shotedCount;
			if ( ableShot < 0 )
				ableShot = 0;
			return ableShot;
		}
		else
			return 0;
	}
	
	public void increaseShotedShipsAmount () {
		this.shotedCount ++;
	}
	
	public void decreaseAmount () {
		if  ( this.count > 0 )
			this.count --;
//		if ( shotedShips > amount ) {
//			shotedShips = amount;
//		}
	}
	public long getTurnId ()
	{
		return turnId;
	}
	public void setTurnId ( long turnId )
	{
		this.turnId = turnId;
	}
	public long getUserId ()
	{
		return userId;
	}
	public void setUserId ( long userId )
	{
		this.userId = userId;
	}
}
