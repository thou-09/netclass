package com.itany.netclass.exception;

/**
 * 课程类别存在性异常
 *
 * @author Thou
 * @date 2022/9/6
 */
public class CourseTypeExistException extends Exception {

    private static final long serialVersionUID = 27859112564298909L;

    public CourseTypeExistException() {
        super();
    }

    public CourseTypeExistException(String message) {
        super(message);
    }

    public CourseTypeExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseTypeExistException(Throwable cause) {
        super(cause);
    }

    protected CourseTypeExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
