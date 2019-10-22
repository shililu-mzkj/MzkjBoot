package org.mzkj.boot.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @title 自定义配置
 * @description 
 * @author shililu
 * @updateTime 2019-10-19 10:09 
 * @throws 
 */
@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfig {
    /**
     * 不需要拦截的地址
     */
    private IgnoreConfig ignores;
}
