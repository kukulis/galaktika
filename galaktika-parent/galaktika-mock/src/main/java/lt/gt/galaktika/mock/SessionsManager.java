package lt.gt.galaktika.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lt.gt.galaktika.model.dao.IUserDao;
import lt.gt.galaktika.model.entity.noturn.User;

@Service
//@Scope(value = "singleton")
public class SessionsManager {
	private int lastSessionId=1;
	
	@Autowired
	IUserDao userDao;
	
//	private SessionsManager () {
//		System.out.println( "Sessions manager constructor called" );
//	}
	
//	private static class InstanceHolder {
//		static SessionsManager INSTANCE = new SessionsManager();
//	}
//	public static SessionsManager getInstance() {
//		return InstanceHolder.INSTANCE;
//	}
	
	public SessionMock login ( String login, String password ) {
		User user = userDao.getByLogin( login );
		if ( user != null && password.equals(user.getPassword() ) ) {
			SessionMock sess = new SessionMock (getNextSessionId());
			sess.setUser( user );
			sess.setValid( true );
			return sess;
		}
		else return new SessionMock( getNextSessionId());
	}
	
	public int getNextSessionId () {
		return this.lastSessionId++;
	}
}
