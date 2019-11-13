package org.mzkj.boot.common.util;

import cn.hutool.core.util.StrUtil;
import org.mzkj.boot.common.exception.MzkjException;
import org.springframework.util.StringUtils;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName ExceptionUtils.java
 * @Description
 * @createTime 2019年11月13日 14:41:00
 */
public class ExceptionUtils {


    private ExceptionUtils() {
    }

    /**
     * 返回一个新的异常，统一构建，方便统一处理
     *
     * @param msg 消息
     * @param t   异常信息
     * @return 返回异常
     */
    public static MzkjException get(String msg, Throwable t, Object... params) {
        return new MzkjException(StrUtil.format(msg, params), t);
    }

    /**
     * 重载的方法
     *
     * @param msg 消息
     * @return 返回异常
     */
    public static MzkjException get(String msg, Object... params) {
        return new MzkjException(StrUtil.format(msg, params));
    }

    /**
     * 重载的方法
     *
     * @param t 异常
     * @return 返回异常
     */
    public static MzkjException get(Throwable t) {
        return new MzkjException(t);
    }

}
