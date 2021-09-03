package br.com.landisgyr.energyconsumption.exception;

public class MeterControllerException extends Exception {
	private static final long serialVersionUID = 1L;

	public MeterControllerException() {
		super();
	}

	public MeterControllerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MeterControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public MeterControllerException(String message) {
		super(message);
	}

	public MeterControllerException(Throwable cause) {
		super(cause);
	}
}
