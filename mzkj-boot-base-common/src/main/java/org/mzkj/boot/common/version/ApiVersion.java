package org.mzkj.boot.common.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName ApiVersion.java
 * @Description 版本号注解
 * @createTime 2019年11月13日 14:27:00
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * 版本号
     *
     */
    int value();
}
