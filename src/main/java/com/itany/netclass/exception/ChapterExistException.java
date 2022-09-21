package com.itany.netclass.exception;

/**
 * 章节存在性异常
 *
 * @author Thou
 * @date 2022/9/9
 */
public class ChapterExistException extends Exception {

    private static final long serialVersionUID = -469467734098670894L;

    public ChapterExistException() {
        super();
    }

    public ChapterExistException(String message) {
        super(message);
    }

    public ChapterExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChapterExistException(Throwable cause) {
        super(cause);
    }

    protected ChapterExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
