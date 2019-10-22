package org.mzkj.boot.system.util;

import cn.hutool.core.util.ObjectUtil;
import org.mzkj.boot.common.constant.CommonConstant;
import org.mzkj.boot.system.vo.MemberDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @title Spring Security工具类
 * @description 
 * @author admin 
 * @updateTime 2019-10-22 11:04 
 * @throws 
 */
public class SecurityUtil {
    /**
     * 获取当前登录用户用户名
     *
     * @return 当前登录用户用户名
     */
    public static String getCurrentUsername() {
        MemberDetails currentUser = getCurrentUser();
        return ObjectUtil.isNull(currentUser) ? CommonConstant.ANONYMOUS_NAME : currentUser.getUsername();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 当前登录用户信息，匿名登录时，为null
     */
    public static MemberDetails getCurrentUser() {
        Object userInfo = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userInfo instanceof UserDetails) {
            return (MemberDetails) userInfo;
        }
        return null;
    }
}
