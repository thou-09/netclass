package com.itany.netclass.exception;

/**
 * 资源存在性异常
 *
 * @author Thou
 * @date 2022/9/9
 */
public class ResourceExistException extends Exception {

    private static final long serialVersionUID = 5361361984607836555L;

    public ResourceExistException() {
        super();
    }

    public ResourceExistException(String message) {
        super(message);
    }

    public ResourceExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceExistException(Throwable cause) {
        super(cause);
    }

    protected ResourceExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
