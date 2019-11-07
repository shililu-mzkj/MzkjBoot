package org.mzkj.boot.system.modules.user.controller;

import org.mzkj.boot.common.base.CommonResult;
import org.mzkj.boot.common.base.Status;
import org.mzkj.boot.system.modules.user.po.req.LoginReq;
import org.mzkj.boot.system.modules.user.po.response.LoginRes;
import org.mzkj.boot.system.util.JwtUtil;
import org.mzkj.boot.common.exception.SecurityException;

import lombok.extern.slf4j.Slf4j;
import org.mzkj.boot.system.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @title 登录-注销
 * @description
 * @author shililu
 * @updateTime 2019-10-19 16:00
 * @throws
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     */
    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody LoginReq loginReq) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String jwt = jwtUtil.createJWT(authentication,loginReq.getRememberMe());
        return CommonResult.ofSuccess(new LoginRes(jwt));
    }

    /**
     * 登录
     */
    @PostMapping("/userInfo")
    public CommonResult userInfo() {
        return CommonResult.ofSuccess(SecurityUtil.getCurrentUser());
    }

  /**
   * @title 退出
   * @description 
   * @author shililu
   * @updateTime 2019-10-19 16:08 
   * @throws 
   */
    @PostMapping("/logout")
    public CommonResult logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(Status.UNAUTHORIZED);
        }
        return CommonResult.ofStatus(Status.LOGOUT);
    }
}
