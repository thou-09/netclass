package com.itany.netclass.exception;

/**
 * 服务失败异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3267113382536328108L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
