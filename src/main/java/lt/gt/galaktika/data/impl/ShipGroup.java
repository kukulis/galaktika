package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IShip;
import lt.gt.galaktika.data.IShipGroup;

public class ShipGroup implements IShipGroup
{
	private IShip ship;
	private int amount=1;
	private int shotedShips = 0;
	private long containerId;
	private int numberInContainer;
	
	public ShipGroup(IShip ship)
	{
		super();
		this.ship = ship;
	}
	
	public IShip getShip ()
	{
		return ship;
	}
	public void setShip ( IShip ship )
	{
		this.ship = ship;
	}
	public int getAmount ()
	{
		return amount;
	}
	public void setAmount ( int amount )
	{
		this.amount = amount;
	}
	
	public int getShotedShips() {
		return shotedShips;
	}
	public void setShotedShips(int shotsAmount) {
		this.shotedShips = shotsAmount;
	}
	
	public int getAbleShotAmount () {
		if ( getShip().getGuns() > 0 ) {
			int ableShot = amount - shotedShips;
			if ( ableShot < 0 )
				ableShot = 0;
			return ableShot;
		}
		else
			return 0;
	}
	public void increaseShotedShipsAmount () {
		this.shotedShips ++;
	}
	
	public void decreaseAmount () {
		if  ( this.amount > 0 )
			this.amount --;
//		if ( shotedShips > amount ) {
//			shotedShips = amount;
//		}
	}

	@Override
	public void setContainerId ( long containerId )
	{
		this.containerId = containerId;
	}

	@Override
	public long getContainerId ()
	{
		return this.containerId;
	}

	@Override
	public void setNumberInContainer ( int n )
	{
		this.numberInContainer = n;
		
	}

	@Override
	public int getNumberInContainer ()
	{
		return numberInContainer;
	}
	
	

}
