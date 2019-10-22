package org.mzkj.boot.system.modules.user.controller;

import cn.hutool.core.collection.CollUtil;
import org.mzkj.boot.common.base.CommonResult;
import org.mzkj.boot.common.base.PageResult;
import org.mzkj.boot.common.base.Status;
import org.mzkj.boot.common.payload.PageCondition;
import org.mzkj.boot.system.modules.user.model.Member;
import org.mzkj.boot.system.modules.user.service.MonitorService;

import org.mzkj.boot.system.util.PageUtil;
import org.mzkj.boot.system.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.mzkj.boot.common.exception.SecurityException;

import java.util.List;

/**
 * @title 监控 Controller，在线用户，手动踢出用户等功能
 * @description
 * @author shililu
 * @updateTime 2019-10-19 16:35
 * @throws
 */
@Slf4j
@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 在线用户列表
     *
     * @param pageCondition 分页参数
     */
    @GetMapping("/online/user")
    public CommonResult onlineUser(PageCondition pageCondition) {
        PageUtil.checkPageCondition(pageCondition, PageCondition.class);
        PageResult<Member> pageResult = monitorService.onlineUser(pageCondition);
        return CommonResult.ofSuccess(pageResult);
    }

    /**
     * 批量踢出在线用户
     *
     * @param names 用户名列表
     */
    @DeleteMapping("/online/user/kickout")
    public CommonResult kickoutOnlineUser(@RequestBody List<String> names) {
        if (CollUtil.isEmpty(names)) {
            throw new SecurityException(Status.PARAM_NOT_NULL);
        }
        if (names.contains(SecurityUtil.getCurrentUsername())){
            throw new SecurityException(Status.KICKOUT_SELF);
        }
        monitorService.kickout(names);
        return CommonResult.ofSuccess();
    }
}
