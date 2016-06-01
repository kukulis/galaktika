package lt.gt.galaktika.core.exception;

public class GalaktikaRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = 4957350037607732802L;

	public GalaktikaRuntimeException()
	{
		super();
	}

	public GalaktikaRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GalaktikaRuntimeException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public GalaktikaRuntimeException(String message)
	{
		super(message);
	}

	public GalaktikaRuntimeException(Throwable cause)
	{
		super(cause);
	}

}
