package com.wcsoft.exception;

public class RoperRuntimeException extends RuntimeException {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8944090653882661730L;

	public RoperRuntimeException() {
    }

    public RoperRuntimeException(String message) {
        super(message);
    }

    public RoperRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoperRuntimeException(Throwable cause) {
        super(cause);
    }

    public RoperRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}