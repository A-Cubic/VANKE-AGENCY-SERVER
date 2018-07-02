package org.wanke.com.exception;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1041023091456199062L;

	public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
