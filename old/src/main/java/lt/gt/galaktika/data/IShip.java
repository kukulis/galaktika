package lt.gt.galaktika.data;

public interface IShip extends Identifiable
{
	public String getShipSerie();
	public void setShipSerie( String s );
	
	public double getAttack ();
	public void setAttack ( double attack );
	public int getGuns ();
	public void setGuns ( int guns );
	public double getDeffence ();
	public void setDeffence ( double deffence );
	public double getCargo ();
	public void setCargo ( double cargo );
	public double getEnginePower ();
	public void setEnginePower ( double enginePower );
	public double getBrutoMass ();
	public void setBrutoMass ( double brutoMass );
	public double getLoadAmount ();
	public void setLoadAmount ( double loadAmount );
}
