package com.itany.netclass.exception;

/**
 * 用户存在性异常
 *
 * @author Thou
 * @date 2022/9/6
 */
public class UserExistException extends Exception {

    private static final long serialVersionUID = -109746353595462209L;

    public UserExistException() {
        super();
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

    protected UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
