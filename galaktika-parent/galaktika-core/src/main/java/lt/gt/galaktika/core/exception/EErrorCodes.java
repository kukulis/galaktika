package lt.gt.galaktika.core.exception;

public enum EErrorCodes {
	RANDOM_ERROR(0),
	DELETE_UNOWN_FLEET(1); // TODO move web error codes to web sub project
	
	private int code;
	private EErrorCodes(int c) {
		this.code = c;
	}
	
	public int getCode () { return code; }
}
