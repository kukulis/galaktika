package lt.gt.sgalaktika;

public class  ShipGroup {
	private String name;
	private Ship ship;
	private int amount=1;
	private int shotedShips = 0;
	
	public ShipGroup ( String groupName, Ship ship ) {
		name=groupName;
		this.ship = ship;
	}
	public ShipGroup ( Ship ship ) {
		name="G"+ship.getShipSerie();
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void decreaseAmount () {
		if  ( this.amount > 0 )
			this.amount --;
//		if ( shotedShips > amount ) {
//			shotedShips = amount;
//		}
	}
}
