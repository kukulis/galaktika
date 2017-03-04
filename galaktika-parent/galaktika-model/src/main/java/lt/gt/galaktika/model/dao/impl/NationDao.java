package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.INationDao;
import lt.gt.galaktika.model.entity.noturn.DNation;

@Deprecated // DNationDao instead
@Repository
@Transactional
public class NationDao implements INationDao
{
	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession ()
	{
		return _sessionFactory.getCurrentSession();
	}

	public List<DNation> getNationByUser ( long userId )
	{
		Query query = getSession().createQuery("from DNation where userId = :userId").setParameter("userId", userId);
		return query.list();
	}

	@Override
	public DNation getNation ( long nationId )
	{
		
		// TODO Auto-generated method stub
//		Query query = getSession().createQuery("from DNation where nationId = :nationId").setParameter("nationId", nationId);
//		return (DNation) query.uniqueResult();
//		
		return (DNation) getSession().get( DNation.class, nationId );
	}
	
	
}
