package com.itany.netclass.exception;

/**
 * 请求参数错误异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class RequestParameterErrorException extends Exception {

    private static final long serialVersionUID = 1064884543696445485L;

    public RequestParameterErrorException() {
    }

    public RequestParameterErrorException(String message) {
        super(message);
    }

    public RequestParameterErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestParameterErrorException(Throwable cause) {
        super(cause);
    }

    public RequestParameterErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
