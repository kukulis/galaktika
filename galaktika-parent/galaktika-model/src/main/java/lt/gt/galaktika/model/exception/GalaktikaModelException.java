package lt.gt.galaktika.model.exception;

import lt.gt.galaktika.core.exception.EErrorCodes;
import lt.gt.galaktika.core.exception.GalaktikaRuntimeException;

public class GalaktikaModelException extends GalaktikaRuntimeException {
	private static final long serialVersionUID = 2706786252833644798L;

	public GalaktikaModelException() {
		super();
	}

	public GalaktikaModelException(EErrorCodes errorCode, String message) {
		super(errorCode, message);
	}

	public GalaktikaModelException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GalaktikaModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public GalaktikaModelException(String message) {
		super(message);
	}

	public GalaktikaModelException(Throwable cause) {
		super(cause);
	}
}
