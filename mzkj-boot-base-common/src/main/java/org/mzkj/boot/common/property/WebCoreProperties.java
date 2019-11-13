package org.mzkj.boot.common.property;

import lombok.Data;
import org.mzkj.boot.common.version.VersionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName WebCoreProperties.java
 * @Description
 * @createTime 2019年11月13日 17:38:00
 */
@Data
@Component
@ConfigurationProperties(prefix = "mzkj-boot.web-core")
public class WebCoreProperties {
    /**
     * 是否开启
     */
    private boolean enabled = true;

    @NestedConfigurationProperty
    private VersionProperties version = new VersionProperties();
}
