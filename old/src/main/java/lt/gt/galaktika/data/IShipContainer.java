package lt.gt.galaktika.data;

import java.util.List;

public interface IShipContainer extends Identifiable
{
	/**
	 * TODO change to imutable collection and add methods for modifications
	 * @return
	 */
	public List <IShipGroup> getShipGroups();
	public void addShipGroup ( IShipGroup group );
	
}
