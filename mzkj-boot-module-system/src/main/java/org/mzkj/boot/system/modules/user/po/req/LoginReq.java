package org.mzkj.boot.system.modules.user.po.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName LoginRequest.java
 * @Description 登录请求参数
 * @createTime 2019年10月19日 16:01:00
 */
@Data
public class LoginReq{
    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

}
