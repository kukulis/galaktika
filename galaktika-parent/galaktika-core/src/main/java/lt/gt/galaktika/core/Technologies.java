package lt.gt.galaktika.core;

public class Technologies
{
	private double attack=1, defence=1, cargo=1, engines=1;

	public double getAttack ()
	{
		return attack;
	}

	public void setAttack ( double attack )
	{
		this.attack = attack;
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

	public double getEngines ()
	{
		return engines;
	}

	public void setEngines ( double engines )
	{
		this.engines = engines;
	}

	@Override
	public String toString ()
	{
		return "["+attack+";"+defence+";"+engines+";"+cargo + "]";
	}
	
	
}
