package lt.gt.sgalaktika;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table ( name="shipgroups" )
public class  ShipGroup {
	public ShipGroup() {
		// TODO Auto-generated constructor stub
	}
	
	@Id @GeneratedValue
	private int group_id;

	// not sure if needed
	private String name;
	
	@ManyToOne @JoinColumn(name="ship_id")
	private Ship ship;
	private int amount=1;
	private int shotedShips = 0;
	
	@ManyToOne @JoinColumn(name="fleet_id")
	private Fleet fleet;
	
	private int numberInFleet;
	
	public ShipGroup ( String groupName, Ship ship ) {
		name=groupName;
		this.ship = ship;
	}
	public ShipGroup ( Ship ship ) {
		name="G"+ship.getShipSerie();
		this.ship = ship;
	}
	
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	
	public int getNumberInFleet() {
		return numberInFleet;
	}
	public void setNumberInFleet(int numberInFleet) {
		this.numberInFleet = numberInFleet;
	}
	@JsonIgnore
	public Fleet getFleet() {
		return fleet;
	}
	public void setFleet(Fleet fleet) {
		this.fleet = fleet;
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
	@JsonIgnore
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
