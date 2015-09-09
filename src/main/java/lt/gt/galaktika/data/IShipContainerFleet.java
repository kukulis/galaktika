package lt.gt.galaktika.data;

/**
 * Protects from getting IFleet fields that are not loaded
 * @author giedrius
 *
 */
public interface IShipContainerFleet extends Identifiable,IFleet, IShipContainer
{
	public int calculateShips();
	public int calculateShipsWithGuns();
	public int calculateAbleShotShipCount();
	public int calculateShots();
	public IShipGroup selectAttackerGroup(int shipNumber);
	public void resetShotsAmount();
	public IShipGroup selectAnyGroup (int shipNumber);
}
