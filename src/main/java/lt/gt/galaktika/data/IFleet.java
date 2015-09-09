package lt.gt.galaktika.data;

public interface IFleet extends Identifiable, IFillInfo
{
	public String getName();
	public void setName (String n);
	public String getCode();
	public void setCode ( String c );
	
}
