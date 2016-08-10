package lt.gt.galaktika.model.exception;

import lt.gt.galaktika.core.exception.GalaktikaException;

public class FleetContractException extends GalaktikaException {
	private static final long serialVersionUID = -8382104187713988184L;

	public FleetContractException() {
		super();
	}

	public FleetContractException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FleetContractException(String message, Throwable cause) {
		super(message, cause);
	}

	public FleetContractException(String message) {
		super(message);
	}

	public FleetContractException(Throwable cause) {
		super(cause);
	}
}
