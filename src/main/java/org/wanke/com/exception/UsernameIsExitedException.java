package org.wanke.com.exception;

public class UsernameIsExitedException extends RuntimeException {
	private static final long serialVersionUID = -2595594787839157158L;

	public UsernameIsExitedException(String message) {
        super(message);
    }
}
