package lt.gt.galaktika.core;

import java.io.Serializable;

public class Ship implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5302270010215839765L;
	private long id;
	private String name;
	private double attack;
	private int guns;
	private double defence;
	private double speed;
	private double cargo;
	private double totalMass;
	private long buildTurnId; // should not reference to turn, use as informative field only
	
	public Ship()
	{
	}
	
	public Ship( String name, double attack, int guns, double defence, double speed, double cargo)
	{
		this.speed = speed;
		this.attack = attack;
		this.guns = guns;
		this.defence = defence;
		this.cargo = cargo;
		this.name = name;
	}
	
	public Ship(long id, String name, double attack, int guns, double defence, double speed, double cargo,
			double totalMass, long buildTurnId) {
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.guns = guns;
		this.defence = defence;
		this.speed = speed;
		this.cargo = cargo;
		this.totalMass = totalMass;
		this.buildTurnId = buildTurnId;
	}

	public Ship ( String name ) {
		this.name=name;
	}
	
	public long getId ()
	{
		return id;
	}
	public void setId ( long id )
	{
		this.id = id;
	}
	public double getTotalMass ()
	{
		return totalMass;
	}
	public void setTotalMass ( double totalMass )
	{
		this.totalMass = totalMass;
	}
	public double getSpeed ()
	{
		return speed;
	}
	public void setSpeed ( double speed )
	{
		this.speed = speed;
	}
	public double getAttack ()
	{
		return attack;
	}
	public void setAttack ( double attack )
	{
		this.attack = attack;
	}
	public int getGuns ()
	{
		return guns;
	}
	public void setGuns ( int guns )
	{
		this.guns = guns;
	}
	public double getDefence ()
	{
		return defence;
	}
	public void setDefence ( double defence )
	{
		this.defence = defence;
	}
	public double getCargo ()
	{
		return cargo;
	}
	public void setCargo ( double cargo )
	{
		this.cargo = cargo;
	}
	public String getName ()
	{
		return name;
	}
	public void setName ( String name )
	{
		this.name = name;
	}

	public long getBuildTurnId ()
	{
		return buildTurnId;
	}

	public void setBuildTurnId ( long buildTurnId )
	{
		this.buildTurnId = buildTurnId;
	}
	
	public String toString () {
		return "["+id+":"+name+"]"+"{"+attack+"x"+guns+ ";"+defence+";"+speed+";"+cargo+ "}"+"("+totalMass+")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
