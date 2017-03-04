package lt.gt.galaktika.mock;

import lt.gt.galaktika.model.entity.noturn.User;

public class SessionMock {
	int id;
	User user;
	boolean valid;
	
	public SessionMock ( int id) {
		this.id=id;
	}
	
	public int getId () {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
