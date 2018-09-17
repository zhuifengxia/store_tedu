package store.ex;

public class PasswordNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException() {
		super();
	}

	public PasswordNotMatchException(String message) {
		super(message);
	}

	public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordNotMatchException(Throwable cause) {
		super(cause);
	}

}
