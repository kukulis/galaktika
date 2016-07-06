package lt.gt.galaktika.core.planet;

public class ShipDesign
{
	private long designId;
	private String designName;
	private double engineMass;
	private double cargoMass;
	private double attackMass;
	private int guns;
	private double defenceMass;
	
	public ShipDesign(String designName)
	{
		super();
		this.designName = designName;
	}
	public ShipDesign()
	{
		super();
	}
	public double getCargoMass ()
	{
		return cargoMass;
	}
	public void setCargoMass ( double cargoMass )
	{
		this.cargoMass = cargoMass;
	}
	public double getAttackMass ()
	{
		return attackMass;
	}
	public void setAttackMass ( double attackMass )
	{
		this.attackMass = attackMass;
	}
	public int getGuns ()
	{
		return guns;
	}
	public void setGuns ( int guns )
	{
		this.guns = guns;
	}
	public double getDefenceMass ()
	{
		return defenceMass;
	}
	public void setDefenceMass ( double defenceMass )
	{
		this.defenceMass = defenceMass;
	}
	public String getDesignName ()
	{
		return designName;
	}
	public void setDesignName ( String designName )
	{
		this.designName = designName;
	}
	public long getDesignId ()
	{
		return designId;
	}
	public void setDesignId ( long designId )
	{
		this.designId = designId;
	}
	public double getEngineMass ()
	{
		return engineMass;
	}
	public void setEngineMass ( double engineMass )
	{
		this.engineMass = engineMass;
	}
	
}
