package lt.gt.galaktika.model;

public class DataSearchLimits
{
	private int limitFrom=0;
	private int limitAmount=10;
	
	public DataSearchLimits() {}
	
	public DataSearchLimits(Integer from, Integer amount) {
		if ( from != null)
			limitFrom=from;
		
		if ( amount != null)
			limitAmount=amount;
	}
	public int getLimitFrom ()
	{
		return limitFrom;
	}
	public void setLimitFrom ( int limitFrom )
	{
		this.limitFrom = limitFrom;
	}
	public int getLimitAmount ()
	{
		return limitAmount;
	}
	public void setLimitAmount ( int limitAmount )
	{
		this.limitAmount = limitAmount;
	}
}
