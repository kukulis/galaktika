package lt.gt.galaktika.data.impl;

import lt.gt.galaktika.data.IShip;

public class Ship implements IShip
{
	private long ID;
	private String shipSerie;
	private double attack;
	private int guns;
	private double deffence;
	private double cargo;
	private double enginePower;
	private double brutoMass;
	private double loadAmount;
	
	public Ship ( String shipSer ) {
		shipSerie = shipSer;
	}

	public long getID ()
	{
		return ID;
	}

	public void setID ( long iD )
	{
		ID = iD;
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

	public double getDeffence ()
	{
		return deffence;
	}

	public void setDeffence ( double deffence )
	{
		this.deffence = deffence;
	}

	public double getCargo ()
	{
		return cargo;
	}

	public void setCargo ( double cargo )
	{
		this.cargo = cargo;
	}

	public double getEnginePower ()
	{
		return enginePower;
	}

	public void setEnginePower ( double enginePower )
	{
		this.enginePower = enginePower;
	}

	public double getBrutoMass ()
	{
		return brutoMass;
	}

	public void setBrutoMass ( double brutoMass )
	{
		this.brutoMass = brutoMass;
	}

	public double getLoadAmount ()
	{
		return loadAmount;
	}

	public void setLoadAmount ( double loadAmount )
	{
		this.loadAmount = loadAmount;
	}

	@Override
	public String getShipSerie ()
	{
		return shipSerie;
	}

	@Override
	public void setShipSerie ( String s )
	{
		shipSerie = s;
	}
	
	
	
}
