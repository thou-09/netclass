package com.itany.netclass.exception;

/**
 * 状态错误异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class StatusErrorException extends Exception{

    private static final long serialVersionUID = -5035677353145757069L;

    public StatusErrorException() {
    }

    public StatusErrorException(String message) {
        super(message);
    }

    public StatusErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusErrorException(Throwable cause) {
        super(cause);
    }

    public StatusErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
