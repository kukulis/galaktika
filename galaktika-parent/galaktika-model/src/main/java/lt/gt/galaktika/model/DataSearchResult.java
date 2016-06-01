package lt.gt.galaktika.model;

import java.util.List;

public class  DataSearchResult <T> 
{
	private List <T> records;
	private long totalAmount;


	public List<T> getRecords ()
	{
		return records;
	}

	public void setRecords ( List<T> records )
	{
		this.records = records;
	}

	public long getTotalAmount ()
	{
		return totalAmount;
	}

	public void setTotalAmount ( long totalAmount )
	{
		this.totalAmount = totalAmount;
	}
}
