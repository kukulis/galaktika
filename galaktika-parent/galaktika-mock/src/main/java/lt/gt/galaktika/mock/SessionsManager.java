package lt.gt.galaktika.mock;

public class SessionsManager {
	private int lastSessionId=1;
	
	private SessionsManager () {
		System.out.println( "Sessions manager constructor called" );
	}
	
	private static class InstanceHolder {
		static SessionsManager INSTANCE = new SessionsManager();
	}
	public static SessionsManager getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	public SessionMock login ( String login, String password ) {
		return new SessionMock (getNextSessionId());
	}
	
	public int getNextSessionId () {
		return this.lastSessionId++;
	}
}
