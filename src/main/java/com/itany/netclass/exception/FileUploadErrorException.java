package com.itany.netclass.exception;

/**
 * 文件上传错误异常
 *
 * @author Thou
 * @date 2022/8/30
*/
public class FileUploadErrorException extends Exception{

    private static final long serialVersionUID = -7651340635674045106L;

    public FileUploadErrorException() {
    }

    public FileUploadErrorException(String message) {
        super(message);
    }

    public FileUploadErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadErrorException(Throwable cause) {
        super(cause);
    }

    public FileUploadErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
