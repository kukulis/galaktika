package lt.gt.galaktika.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;

@Repository
@Transactional
public class UserDao implements IUserDao
{

	@PersistenceContext
	private EntityManager em;

	private Session getSession ()
	{
		return em.unwrap(Session.class);
	}

	public Long save ( User user )
	{
		return (Long) getSession().save(user);
	}

	public void delete ( User user )
	{
		getSession().delete(user);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll ()
	{
		return getSession().createQuery("from User").list();
	}

	public User getByEmail ( String email )
	{
		return (User) getSession().createQuery("from User where email = :email").setParameter("email", email)
				.uniqueResult();
	}

	public User getById ( long id )
	{
		return (User) getSession().load(User.class, id);
	}

	public void update ( User user )
	{
		getSession().update(user);
		return;
	}

	@Override
	public User getByLogin ( String login )
	{
		return (User) getSession().createQuery("from User where login = :login").setParameter("login", login)
				.uniqueResult();
	}

} // class UserDao
