package org.mzkj.boot.system.repository;

import org.mzkj.boot.system.BaseAppliction;
import org.mzkj.boot.system.modules.user.mapper.MemberMapper;
import org.mzkj.boot.system.modules.user.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title userMapper测试
 * @description
 * @author admin
 * @updateTime 2019-10-22 11:05
 * @throws
 */
@Slf4j
public class UserDaoTest extends BaseAppliction {

    @Resource
    private MemberMapper memberMapper;

    @Test
    public void findByUsernameIn() {
        List<String> usernameList = Lists.newArrayList("shililu", "user");
        List<Member> userList = memberMapper.findByUsernameIn(usernameList);
        Assert.assertEquals(2, userList.size());
        log.info("【userList】= {}", userList);
    }
}