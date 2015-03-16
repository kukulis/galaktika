package lt.gt.sgalaktika;

public class GalaktikaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1874414699125129451L;

	public GalaktikaException() {
		super();
	}

	public GalaktikaException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public GalaktikaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public GalaktikaException(String arg0) {
		super(arg0);
	}

	public GalaktikaException(Throwable arg0) {
		super(arg0);
	}
}
