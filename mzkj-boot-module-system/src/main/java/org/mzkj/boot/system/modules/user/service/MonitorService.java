package org.mzkj.boot.system.modules.user.service;

import cn.hutool.core.util.StrUtil;
import org.mzkj.boot.common.base.PageResult;
import org.mzkj.boot.common.constant.CommonConstant;
import org.mzkj.boot.common.payload.PageCondition;
import org.mzkj.boot.system.modules.user.mapper.MemberMapper;
import org.mzkj.boot.system.modules.user.model.Member;
import org.mzkj.boot.system.util.RedisUtil;
import org.mzkj.boot.system.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MonitorService {
    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private MemberMapper memberMapper;

    /**
     * 在线用户分页列表
     *
     * @param pageCondition 分页参数
     * @return 在线用户分页列表
     */
    public PageResult<Member> onlineUser(PageCondition pageCondition) {
        PageResult<String> keys = redisUtil.findKeysForPage(CommonConstant.REDIS_JWT_KEY_PREFIX + CommonConstant.SYMBOL_STAR, pageCondition.getCurrentPage(), pageCondition.getPageSize());
        List<String> rows = keys.getRows();
        Long total = keys.getTotal();

        // 根据 redis 中键获取用户名列表
        List<String> usernameList = rows.stream()
                .map(s -> StrUtil.subAfter(s, CommonConstant.REDIS_JWT_KEY_PREFIX, true))
                .collect(Collectors.toList());
        // 根据用户名查询用户信息
        List<Member> userList = memberMapper.findByUsernameIn(usernameList);
        return new PageResult<>(userList, total);
    }

    /**
     * 踢出在线用户
     *
     * @param names 用户名列表
     */
    public void kickout(List<String> names) {
        // 清除 Redis 中的 JWT 信息
        List<String> redisKeys = names.parallelStream()
                .map(s -> CommonConstant.REDIS_JWT_KEY_PREFIX + s)
                .collect(Collectors.toList());
        redisUtil.delete(redisKeys);

        // 获取当前用户名
        String currentUsername = SecurityUtil.getCurrentUsername();
        names.parallelStream()
                .forEach(name -> {
                    // TODO: 通知被踢出的用户已被当前登录用户踢出，
                    //  后期考虑使用 websocket 实现，具体伪代码实现如下。
                    //  String message = "您已被用户【" + currentUsername + "】手动下线！";
                    log.debug("用户【{}】被用户【{}】手动下线！", name, currentUsername);
                });
    }
}
