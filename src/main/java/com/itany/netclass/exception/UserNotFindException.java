package com.itany.netclass.exception;

/**
 * 用户不存在异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class UserNotFindException extends Exception {

    private static final long serialVersionUID = 3889777889156112113L;

    public UserNotFindException() {
    }

    public UserNotFindException(String message) {
        super(message);
    }

    public UserNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFindException(Throwable cause) {
        super(cause);
    }

    public UserNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
