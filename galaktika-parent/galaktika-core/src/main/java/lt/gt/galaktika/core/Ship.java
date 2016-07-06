package lt.gt.galaktika.core;

public class Ship
{
	private long id;
	private double totalMass;
	private double speed;
	private double attack;
	private int guns;
	private double defence;
	private double cargo;
	private String name;
	private long buildTurnId; // should not reference to turn, use as informative field only
	
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
}
