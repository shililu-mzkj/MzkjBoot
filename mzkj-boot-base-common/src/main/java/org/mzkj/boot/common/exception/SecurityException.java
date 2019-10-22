package org.mzkj.boot.common.exception;

import org.mzkj.boot.common.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title 全局异常
 * @description
 * @author shililu
 * @updateTime 2019-10-22 10:59
 * @throws
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityException extends BaseException {
    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
