package org.mzkj.boot.system.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.mzkj.boot.common.base.PageResult;
import org.mzkj.boot.common.constant.CommonConstant;
import org.mzkj.boot.system.BaseAppliction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

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
//        PageResult pageResult = redisUtil.findKeysForPage(CommonConstant.REDIS_JWT_KEY_PREFIX + CommonConstant.SYMBOL_STAR, 2, 1);
//        log.info("【pageResult】= {}", JSONUtil.toJsonStr(pageResult));
    }
    @Test
    public void strUtilTest(){
        System.out.println(StrUtil.equals("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDcyODA2Mzc3NjYxMDA5OTIwIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NzMwOTI3NjksImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJURVNUIn1dLCJleHAiOjE1NzMwOTMzNjl9.ABk4F64QzG7n-VOMX7jnaPaiajaLCfbH4AA2tThNFuA","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDcyODA2Mzc3NjYxMDA5OTIwIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1NzMwOTI3NjksImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJURVNUIn1dLCJleHAiOjE1NzMwOTMzNjl9.ABk4F64QzG7n-VOMX7jnaPaiajaLCfbH4AA2tThNFuA"));
    }
}