package lt.gt.galaktika.data;

public interface IShipGroup
{
	public IShip getShip ();
	public void setShip ( IShip ship );
	public int getAmount ();
	public void setAmount ( int amount );
	
	public void setContainerId( long containerId );
	public long  getContainerId();
	public void setNumberInContainer( int n);
	public int getNumberInContainer();
	
	public int getShotedShips();
	public void setShotedShips( int s );
	public int getAbleShotAmount();
	public void decreaseAmount();
	public void increaseShotedShipsAmount();
}
