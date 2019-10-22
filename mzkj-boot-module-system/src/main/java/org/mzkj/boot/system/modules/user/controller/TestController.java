package org.mzkj.boot.system.modules.user.controller;

import org.mzkj.boot.common.base.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @title test
 * @description 
 * @author admin 
 * @updateTime 2019-10-22 11:03
 * @throws 
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public CommonResult list() {
        log.info("测试列表查询");
        return CommonResult.ok("测试列表查询");
    }

}
