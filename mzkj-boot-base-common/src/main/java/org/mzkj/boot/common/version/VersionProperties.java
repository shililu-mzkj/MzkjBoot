package org.mzkj.boot.common.version;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName VersionProperties.java
 * @Description 版本配置
 * @createTime 2019年11月13日 14:35:00
 */

@Data
@Component
@ConfigurationProperties(prefix = "mzkj-boot.web-core.version")
public class VersionProperties {

    /**
     * 是否开启
     */
    private boolean enabled = true;

}