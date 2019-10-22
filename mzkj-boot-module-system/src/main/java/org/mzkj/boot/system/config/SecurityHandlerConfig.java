package org.mzkj.boot.system.config;

import org.mzkj.boot.common.base.Status;
import org.mzkj.boot.common.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName SecurityHandlerConfig.java
 * @Description security处理器
 * @createTime 2019年10月19日 17:37:00
 */
@Configuration
public class SecurityHandlerConfig {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response, Status.FORBIDDEN, null);
    }
}
