package store.ex;

public class OrderCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderCreationException() {
		super();
	}

	public OrderCreationException(String message) {
		super(message);
	}

	public OrderCreationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public OrderCreationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderCreationException(Throwable cause) {
		super(cause);
	}

}
