package exception;

//A runtime exception that is thrown only by our code.
public class VerintException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VerintException(Throwable cause) {
		super(cause);
	}

	public VerintException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerintException(String message) {
		super(message);
	}

	public VerintException() {
		super();
	}

}