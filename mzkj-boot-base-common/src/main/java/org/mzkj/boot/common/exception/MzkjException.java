package org.mzkj.boot.common.exception;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName MzkjException.java
 * @Description
 * @createTime 2019年11月13日 14:42:00
 */
public class MzkjException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MzkjException(String message) {
        super(message);
    }

    public MzkjException(Throwable throwable) {
        super(throwable);
    }

    public MzkjException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
