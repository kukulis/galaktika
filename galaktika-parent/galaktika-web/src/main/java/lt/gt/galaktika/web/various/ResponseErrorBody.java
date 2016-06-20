package lt.gt.galaktika.web.various;

public class ResponseErrorBody
{
	private int code;
	private String message;
	private String exceptionMessage;
	
	
	public ResponseErrorBody()
	{
	}
	public ResponseErrorBody(int code, String message)
	{
		this.code = code;
		this.message = message;
	}
	public int getCode ()
	{
		return code;
	}
	public void setCode ( int code )
	{
		this.code = code;
	}
	public String getMessage ()
	{
		return message;
	}
	public void setMessage ( String message )
	{
		this.message = message;
	}
	public String getExceptionMessage ()
	{
		return exceptionMessage;
	}
	public void setExceptionMessage ( String exceptionMessage )
	{
		this.exceptionMessage = exceptionMessage;
	}
}
