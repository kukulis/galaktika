package lt.gt.galaktika.model;

import org.hibernate.criterion.Order;

public class GalaktikaModelUtils
{
	public final static Order createOrder ( String propertyName, ESortMethods sortMethod ) {
		if ( sortMethod == ESortMethods.ASC )
			return Order.asc( propertyName );
		else if ( sortMethod == ESortMethods.DESC )
			return Order.desc(propertyName);
		return null;
	}
}
