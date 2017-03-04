package lt.gt.galaktika.model.dao;

import java.util.List;

import lt.gt.galaktika.model.entity.noturn.User;

public interface IUserDao
{
	public Long save ( User user );
	public void delete ( User user );
	public List<User> getAll ();
	public User getByEmail ( String email );
	public User getByLogin ( String login );
	public User getById ( long id );
	public void update ( User user );
}
