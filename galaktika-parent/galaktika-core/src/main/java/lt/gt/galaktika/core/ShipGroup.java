package lt.gt.galaktika.core;

import java.io.Serializable;

public class ShipGroup implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7426787654165531142L;
//	private Fleet fleet;
	private Ship ship;
	private int count=1;
	
	/**
	 * How many ships did a shot.
	 */
	private int shotedCount=0;
	
	public ShipGroup() {
		
	}
	
	public ShipGroup ( Ship ship ) {
		this.ship=ship;
	}
	
	public ShipGroup(Ship ship, int count)
	{
		this.ship = ship;
		this.count = count;
	}

//	public Fleet getFleet ()
//	{
//		return fleet;
//	}
//	public void setFleet ( Fleet fleet )
//	{
//		this.fleet = fleet;
//	}
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
	
	public int calucalateAbleShotAmount () {
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
	public String toString() {
		return ship.toString()+" count:"+count+"  shotedCount:"+shotedCount;
	}
}
