package com.cubic.api.core.exception;

/**
 * Service异常
 *
 * @author fei.yu
 * @date 2018/06/09
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -4218262170344207786L;

	public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
