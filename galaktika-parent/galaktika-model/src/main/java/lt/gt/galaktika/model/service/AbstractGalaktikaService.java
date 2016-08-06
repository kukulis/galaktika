package lt.gt.galaktika.model.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;

/**
 * 
 * @param <D> database object
 * @param <C> core object
 */
public abstract class AbstractGalaktikaService <D,C> {
	
	public D mapToDbObject ( C coreObject ) {
		D dbObject = createDbObject();
		try {
			BeanUtils.copyProperties( dbObject, coreObject );
		} catch ( InvocationTargetException | IllegalAccessException ite ) {
			throw new GalaktikaRuntimeException( ite );
		}

		return dbObject;
	}
	public C mapToCoreObject ( D dbObject ) {
		C coreObject = createCoreObject();
		try {
			BeanUtils.copyProperties( coreObject, dbObject );
		} catch ( InvocationTargetException | IllegalAccessException ite ) {
			throw new GalaktikaRuntimeException( ite );
		}

		return coreObject;
	}
	
	public abstract D createDbObject ();
	public abstract C createCoreObject();
	
}
