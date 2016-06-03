package lt.gt.galaktika.model.dao;

public class DFleetFilter
{
	private boolean hideDeletedFleets=true;
	
	private long filterNationId;

	public long getFilterNationId ()
	{
		return filterNationId;
	}

	public void setFilterNationId ( long filterNationId )
	{
		this.filterNationId = filterNationId;
	}

	public boolean isHideDeletedFleets ()
	{
		return hideDeletedFleets;
	}

	public void setHideDeletedFleets ( boolean hideDeletedFleets )
	{
		this.hideDeletedFleets = hideDeletedFleets;
	}
}
