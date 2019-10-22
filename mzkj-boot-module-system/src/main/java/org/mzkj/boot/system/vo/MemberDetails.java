package org.mzkj.boot.system.vo;

import org.mzkj.boot.system.modules.user.model.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


/**
 * @title 会员详情封装
 * @description 
 * @author admin 
 * @updateTime 2019-10-22 11:04 
 * @throws 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetails implements UserDetails {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 状态，启用-1，禁用-0
     */
    private Integer status;

    public static MemberDetails create(Member member) {
        return new MemberDetails(member.getId(), member.getUsername(), member.getPassword(), member.getStatus());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }

}
