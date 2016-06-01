package lt.gt.galaktika.data;

import java.util.Collection;

public interface IFleetsSpace
{
	Collection <ISpaceTravelerFleet> getFleets();
	ISpaceTravelerFleet getFleet ( long id );
}
