package com.itany.netclass.exception;

/**
 * 积分金币错误异常
 *
 * @author Thou
 * @date 2022/9/13
 */
public class GoldPointsErrorException extends Exception{

    private static final long serialVersionUID = -4351908507508103510L;

    public GoldPointsErrorException() {
        super();
    }

    public GoldPointsErrorException(String message) {
        super(message);
    }

    public GoldPointsErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoldPointsErrorException(Throwable cause) {
        super(cause);
    }

    protected GoldPointsErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
