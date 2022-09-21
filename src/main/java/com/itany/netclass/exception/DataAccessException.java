package com.itany.netclass.exception;

/**
 * 数据访问失败异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = -2706724724567342167L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

	protected DataAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
