package com.itany.netclass.exception;

/**
 * beanFactory 错误异常
 *
 * @author Thou
 * @date 2022/8/31
 */
public class BeanFactoryErrorException extends Exception{

    private static final long serialVersionUID = -7256140927718382631L;

    public BeanFactoryErrorException() {
        super();
    }

    public BeanFactoryErrorException(String message) {
        super(message);
    }

    public BeanFactoryErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanFactoryErrorException(Throwable cause) {
        super(cause);
    }

    protected BeanFactoryErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
