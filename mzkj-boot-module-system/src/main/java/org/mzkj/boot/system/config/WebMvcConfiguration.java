package org.mzkj.boot.system.config;

import org.mzkj.boot.common.handler.WebRequestMappingHandlerMapping;
import org.mzkj.boot.common.property.WebCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @title Spring Boot 2.0 解决跨域问题
 * @description 
 * @author admin 
 * @updateTime 2019-10-22 11:02 
 * @throws 
 */
@Configuration
@Import({
		WebCoreProperties.class
})
@ConditionalOnProperty(prefix = "mzkj-boot.web-core", name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebMvcConfiguration implements WebMvcConfigurer , WebMvcRegistrations {

	private final WebCoreProperties webCoreProperties;

	@Autowired
	public WebMvcConfiguration(WebCoreProperties webCoreProperties) {
		this.webCoreProperties = webCoreProperties;
	}

	@Bean
	@ConditionalOnMissingBean
	public WebMvcConfiguration webMvcConfiguration() {
		return new WebMvcConfiguration(webCoreProperties);
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		/* 是否允许请求带有验证信息 */
		corsConfiguration.setAllowCredentials(true);
		/* 允许访问的客户端域名 */
		corsConfiguration.addAllowedOrigin("*");
		/* 允许服务端访问的客户端请求头 */
		corsConfiguration.addAllowedHeader("*");
		/* 允许访问的方法名,GET POST等 */
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


	/**
	 * 方案一： 默认访问根路径跳转 doc.html页面 （swagger文档页面）
	 * 方案二： 访问根路径改成跳转 index.html页面 （简化部署方案： 可以把前端打包直接放到项目的 webapp，上面的配置）
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("doc.html");
	}


	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return new WebRequestMappingHandlerMapping(webCoreProperties.getVersion());
	}
}
