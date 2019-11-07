package org.mzkj.boot.system.dto;

import lombok.Getter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @title 通用用户
 * @description
 * @author admin
 * @updateTime 2019-11-06 16:44
 * @throws
 */
public class MzkjUser extends User {
	/**
	 * 用户ID
	 */
	@Getter
	private Integer id;


	public MzkjUser(Integer id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
	}
}
