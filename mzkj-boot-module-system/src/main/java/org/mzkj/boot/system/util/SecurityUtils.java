package org.mzkj.boot.system.util;
import lombok.experimental.UtilityClass;
import org.mzkj.boot.system.dto.MzkjUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全工具类
 *
 * @author L.cm
 */
@UtilityClass
public class SecurityUtils {
	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 *
	 * @param authentication
	 * @return MzkjUser
	 * <p>
	 */
	public MzkjUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof MzkjUser) {
			return (MzkjUser) principal;
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public MzkjUser getUser() {
		Authentication authentication = getAuthentication();
		return getUser(authentication);
	}

}
