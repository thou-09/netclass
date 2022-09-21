package com.itany.netclass.exception;

/**
 * 课程不存在异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class CourseExistException extends Exception {

    private static final long serialVersionUID = 2695718793716901716L;

    public CourseExistException() {
    }

    public CourseExistException(String message) {
        super(message);
    }

    public CourseExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseExistException(Throwable cause) {
        super(cause);
    }

    protected CourseExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
