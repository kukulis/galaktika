package lt.gt.galaktika.model.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;
import lt.gt.galaktika.model.dao.IDAO;

/**
 * 
 * @param <D> database object
 * @param <C> core object
 */
public abstract class AbstractGalaktikaService <D,C> {
	
	@Autowired IDAO dao;
	
	public D mapToDbObject ( C coreObject ) {
		if  (coreObject == null )
			return null;
		
		D dbObject = createDbObject();
		try {
			BeanUtils.copyProperties( dbObject, coreObject );
		} catch ( InvocationTargetException | IllegalAccessException ite ) {
			throw new GalaktikaRuntimeException( ite );
		}

		return dbObject;
	}
	public C mapToCoreObject ( D dbObject ) {
		if ( dbObject == null )
			return null;
		
		C coreObject = createCoreObject();
		try {
			BeanUtils.copyProperties( coreObject, dbObject );
		} catch ( InvocationTargetException | IllegalAccessException ite ) {
			throw new GalaktikaRuntimeException( "coreObject.class="+coreObject.getClass()+"  dbObject.class="+dbObject.getClass()+" message="+ite.getMessage() , ite );
		}

		return coreObject;
	}
	
	public D createDbObject () {
		try {
			return getDClazz().newInstance();
		} catch ( IllegalAccessException | InstantiationException iae ) {
			throw new GalaktikaRuntimeException( iae );
		}
	}
	
	// TODO may be its possible to create with reflection
	public abstract C createCoreObject();
	
	public abstract Class<D> getDClazz();
	
	public C create( C c ) {
		D d = mapToDbObject( c );
		d = dao.create( d );
		c = mapToCoreObject( d );
		return c;
	}
	
	public C load ( long id) {
		D d = dao.find( getDClazz(), id);
		return mapToCoreObject( d );
	}
}
