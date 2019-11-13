package org.mzkj.boot.common.handler;

import com.google.common.collect.Maps;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.mzkj.boot.common.util.ExceptionUtils;
import org.mzkj.boot.common.version.ApiVersion;
import org.mzkj.boot.common.version.ApiVesrsionCondition;
import org.mzkj.boot.common.version.VersionProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.mzkj.boot.common.constant.WebCoreConstants.MAPPING.X_PATH_VARIABLE_MAPPING_NAME;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName WebRequestMappingHandlerMapping.java
 * @Description 核心拦截器
 * @createTime 2019年11月13日 14:33:00
 */
@Slf4j
public class WebRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private static final Map<HandlerMethod, RequestMappingInfo> HANDLER_METHOD_REQUEST_MAPPING_INFO_MAP = Maps.newHashMap();
    private VersionProperties versionProperties;
    public WebRequestMappingHandlerMapping(VersionProperties versionProperties) {
        this.versionProperties = versionProperties;
    }

    /**
     * 解决 PathVariable 效率低问题
     *
     * @param handler
     * @param method
     * @param mapping
     */
    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        HandlerMethod handlerMethod = super.createHandlerMethod(handler, method);
        HANDLER_METHOD_REQUEST_MAPPING_INFO_MAP.put(handlerMethod, mapping);
        super.registerHandlerMethod(handler, method, mapping);
    }

    @Override
    protected HandlerMethod lookupHandlerMethod(String lookupPath, HttpServletRequest request) throws Exception {
        String mappingName = request.getHeader(X_PATH_VARIABLE_MAPPING_NAME);
        if (StringUtils.isBlank(mappingName)) {
            return super.lookupHandlerMethod(lookupPath, request);
        }

        log.info("Enable customization mapping > {}", mappingName);
        List<HandlerMethod> handlerMethods = super.getHandlerMethodsForMappingName(mappingName);
        if (CollectionUtils.isEmpty(handlerMethods)) {
            throw ExceptionUtils.get("Method does not exist: {}", mappingName);
        }
        if (handlerMethods.size() > 1) {
            throw ExceptionUtils.get("There are multiple methods: {}", mappingName);
        }

        HandlerMethod handlerMethod = handlerMethods.get(0);
        // 根据处理方法查找RequestMappingInfo, 用于解析路径url中的参数
        RequestMappingInfo requestMappingInfo = HANDLER_METHOD_REQUEST_MAPPING_INFO_MAP.get(handlerMethod);
        if (requestMappingInfo == null) {
            throw ExceptionUtils.get("Method does not exist in map: {}", mappingName);
        }
        super.handleMatch(requestMappingInfo, lookupPath, request);
        return handlerMethod;
    }


    /**
     * 加入版本控制
     *
     * @param method
     * @param handlerType
     * @return
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        if (versionProperties.isEnabled() && info != null) {
            return createApiVersionInfo(method, handlerType, info);
        }else{
            return info;
        }
    }

    @Override
    protected RequestCondition<ApiVesrsionCondition> getCustomTypeCondition(Class<?> handlerType) {
        if (versionProperties.isEnabled()) {
            ApiVersion apiVersion = (ApiVersion) AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
            return createCondition(apiVersion);
        }else{
            return null;
        }
    }

    @Override
    protected RequestCondition<ApiVesrsionCondition> getCustomMethodCondition(Method method) {
        if (versionProperties.isEnabled()) {
            ApiVersion apiVersion = (ApiVersion) AnnotationUtils.findAnnotation(method, ApiVersion.class);
            return createCondition(apiVersion);
        }else{
            return null;
        }
    }

    private RequestMappingInfo createApiVersionInfo(Method method, Class<?> handlerType, RequestMappingInfo info) {
        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (methodAnnotation != null) {
            RequestCondition<?> methodCondition = getCustomMethodCondition(method);
            info = createApiVersionInfo(methodAnnotation, methodCondition).combine(info);
        } else {
            ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
            if (typeAnnotation != null) {
                RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createApiVersionInfo(typeAnnotation, typeCondition).combine(info);
            }
        }
        return info;
    }

    private RequestMappingInfo createApiVersionInfo(ApiVersion annotation, RequestCondition<?> customCondition) {
        int value = annotation.value();
        String[] patterns = new String[1];

        patterns[0] = ("v" + value);
//        patterns[1] = "/{version}";
//        patterns[2] = "/**";

        return new RequestMappingInfo(
                new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(),
                        useTrailingSlashMatch(), getFileExtensions()),
                new RequestMethodsRequestCondition(), new ParamsRequestCondition(),
                new HeadersRequestCondition(), new ConsumesRequestCondition(),
                new ProducesRequestCondition(), customCondition);
    }

    private RequestCondition<ApiVesrsionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVesrsionCondition(apiVersion.value());
    }

}

