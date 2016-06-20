package lt.gt.galaktika.core.exception;

public class GalaktikaRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = 4957350037607732802L;
	
	private EErrorCodes errorCode=EErrorCodes.RANDOM_ERROR;
	
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
	

	public GalaktikaRuntimeException(EErrorCodes errorCode, String message )
	{
		super(message);
		this.errorCode = errorCode;
	}

	public EErrorCodes getErrorCode ()
	{
		return errorCode;
	}
	
	public void setErrorCode ( EErrorCodes errorCode )
	{
		this.errorCode = errorCode;
	}
	
}
