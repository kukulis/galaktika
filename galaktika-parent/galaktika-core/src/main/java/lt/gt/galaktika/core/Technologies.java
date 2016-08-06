package lt.gt.galaktika.core;

import java.io.Serializable;

public class Technologies implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -718863270927378084L;
	private long technologiesId;
	private double attack=1, defence=1, cargo=1, engines=1;
	
	public Technologies() {
	}
	
	public Technologies(long technologiesId, double attack, double defence, double cargo, double engines) {
		this.technologiesId = technologiesId;
		this.attack = attack;
		this.defence = defence;
		this.cargo = cargo;
		this.engines = engines;
	}



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

//	@Override
//	public String toString ()
//	{
//		return "["+attack+";"+defence+";"+engines+";"+cargo + "]";
//	}

	public long getTechnologiesId() {
		return technologiesId;
	}

	public void setTechnologiesId(long technologiesId) {
		this.technologiesId = technologiesId;
	}

	@Override
	public String toString() {
		return "Technologies [technologiesId=" + technologiesId + ", attack=" + attack + ", defence=" + defence
				+ ", cargo=" + cargo + ", engines=" + engines + "]";
	}
}
