package org.mzkj.boot.common.base;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName IStatus.java
 * @Description 封装API返回码
 * @createTime 2019年10月18日 16:45:00
 */
public interface IStatus {
    Integer getCode();

    String getMessage();

}
