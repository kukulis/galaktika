package lt.gt.galaktika.persistence;

import java.util.Collection;

import lt.gt.galaktika.data.IFleet;

public interface IFleetDAO
{   
	public Collection <IFleet> findAllFleets();
	public void create (IFleet fleet);
	public void delete (IFleet fleet);
	public void update (IFleet fleet);
	/**
	 * Loads fleet from storage. Loads only the required info, depending on the given class.
	 * If the fleet class has all the required fields, the new class might not be created for the return. 
	 * 
	 * @param fleet basic fleet info for loading fleet
	 * @param c Available choices: IFleet.class, IShipContainerFleet.class, ISpaceTravelerFleet.class, IFullFleet.class. 
	 * @return
	 */
	public <T extends IFleet> T findById ( IFleet fleet, Class <T>  c );
	

}
