package lt.gt.galaktika.model.dao.impl;

public class DFleetFilter
{
	private boolean hideDeletedFleets=true;
	private long filterNationId;
	private String filterName;

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

	public String getFilterName ()
	{
		return filterName;
	}

	public void setFilterName ( String filterName )
	{
		this.filterName = filterName;
	}
}
