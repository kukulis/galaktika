package lt.gt.galaktika.model;

public class FleetSortData
{
	private ESortMethods idSort=ESortMethods.NONE;
	private ESortMethods nameSort=ESortMethods.NONE;
	public ESortMethods getIdSort ()
	{
		return idSort;
	}
	public void setIdSort ( ESortMethods idSort )
	{
		this.idSort = idSort;
	}
	public ESortMethods getNameSort ()
	{
		return nameSort;
	}
	public void setNameSort ( ESortMethods nameSort )
	{
		this.nameSort = nameSort;
	}
}
