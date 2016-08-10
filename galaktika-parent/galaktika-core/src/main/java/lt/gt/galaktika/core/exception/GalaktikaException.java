package lt.gt.galaktika.core.exception;

public class GalaktikaException extends Exception {
	private static final long serialVersionUID = -2920598195851311479L;

	public GalaktikaException() {
		super();
	}

	public GalaktikaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GalaktikaException(String message, Throwable cause) {
		super(message, cause);
	}

	public GalaktikaException(String message) {
		super(message);
	}

	public GalaktikaException(Throwable cause) {
		super(cause);
	}
}
