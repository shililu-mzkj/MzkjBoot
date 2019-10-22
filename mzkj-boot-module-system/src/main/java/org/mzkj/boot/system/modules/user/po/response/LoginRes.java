package org.mzkj.boot.system.modules.user.po.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName LoginRes.java
 * @Description 登录响应
 * @createTime 2019年10月19日 16:05:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer";

    public LoginRes(String token) {
        this.token = token;
    }
}
