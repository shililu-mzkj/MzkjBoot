package org.mzkj.boot.system.util;

import cn.hutool.json.JSONUtil;
import org.mzkj.boot.common.base.PageResult;
import org.mzkj.boot.common.constant.CommonConstant;
import org.mzkj.boot.system.BaseAppliction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @title 测试RedisUtil
 * @description
 * @author shililu
 * @updateTime 2019-10-19 16:56
 * @throws
 */
@Slf4j
public class RedisUtilTest extends BaseAppliction {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void findKeysForPage() {
        PageResult pageResult = redisUtil.findKeysForPage(CommonConstant.REDIS_JWT_KEY_PREFIX + CommonConstant.SYMBOL_STAR, 2, 1);
        log.info("【pageResult】= {}", JSONUtil.toJsonStr(pageResult));
    }
}