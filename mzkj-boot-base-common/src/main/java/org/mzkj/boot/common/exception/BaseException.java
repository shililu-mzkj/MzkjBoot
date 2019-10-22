package org.mzkj.boot.common.exception;

import org.mzkj.boot.common.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title 异常基类
 * @description 
 * @author shililu
 * @updateTime 2019-10-22 10:58 
 * @throws 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
	private Integer code;
	private String message;
	private Object data;

	public BaseException(Status status) {
		super(status.getMessage());
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	public BaseException(Status status, Object data) {
		this(status);
		this.data = data;
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseException(Integer code, String message, Object data) {
		this(code, message);
		this.data = data;
	}
}
