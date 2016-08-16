package lt.gt.galaktika.model.exception;

import lt.gt.galaktika.core.exception.GalaktikaException;

public class PlanetSurfaceContractException extends GalaktikaException {
	private static final long serialVersionUID = -8184504629029364117L;

	public PlanetSurfaceContractException() {
		super();
	}

	public PlanetSurfaceContractException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PlanetSurfaceContractException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlanetSurfaceContractException(String message) {
		super(message);
	}

	public PlanetSurfaceContractException(Throwable cause) {
		super(cause);
	}
}
