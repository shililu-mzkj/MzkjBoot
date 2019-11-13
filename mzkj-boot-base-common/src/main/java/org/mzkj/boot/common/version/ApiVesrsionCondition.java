package org.mzkj.boot.common.version;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName ApiVesrsionCondition.java
 * @Description 版本条件
 * @createTime 2019年11月13日 14:28:00
 */
public class ApiVesrsionCondition implements RequestCondition<ApiVesrsionCondition> {

    private int apiVersion;

    /**
     * 路径中版本的前缀， 这里用 /v[1-9]/的形式
     */
    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v(\\d+)/");

    private int getApiVersion() {
        return this.apiVersion;
    }

    public ApiVesrsionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVesrsionCondition combine(@NonNull ApiVesrsionCondition other) {
        return new ApiVesrsionCondition(other.getApiVersion());
    }

    @Override
    public ApiVesrsionCondition getMatchingCondition(@NonNull HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getServletPath());
        if (m.find()) {
            Integer version = Integer.valueOf(m.group(1));
            if (version < this.apiVersion) {
                return null;
            }
        }
        return this;
    }

    @Override
    public int compareTo(@NonNull ApiVesrsionCondition other, @NonNull HttpServletRequest request) {
        int i = other.getApiVersion() - this.apiVersion;
        if (i == 0) {
            return 0;
        } else {
            return i > 0 ? 1 : -1;
        }
    }

}

