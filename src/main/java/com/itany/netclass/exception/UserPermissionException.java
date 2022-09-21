package com.itany.netclass.exception;

/**
 * 用户权限异常
 *
 * @author Thou
 * @date 2022/9/3
 */
public class UserPermissionException extends Exception {

    private static final long serialVersionUID = -1072529118921143549L;

    public UserPermissionException() {
        super();
    }

    public UserPermissionException(String message) {
        super(message);
    }

    public UserPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPermissionException(Throwable cause) {
        super(cause);
    }

    protected UserPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
