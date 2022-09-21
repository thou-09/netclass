package com.itany.netclass.exception;

/**
 * 评论存在性异常
 *
 * @author Thou
 * @date 2022/9/9
 */
public class CommentExistException extends Exception {

    private static final long serialVersionUID = -3322495630122981365L;

    public CommentExistException() {
        super();
    }

    public CommentExistException(String message) {
        super(message);
    }

    public CommentExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentExistException(Throwable cause) {
        super(cause);
    }

    protected CommentExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
