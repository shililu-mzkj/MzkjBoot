package org.mzkj.boot.system.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mzkj.boot.system.modules.user.mapper.MemberMapper;
import org.mzkj.boot.system.modules.user.model.Member;
import org.mzkj.boot.system.modules.user.service.IMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName MemberServiceImpl.java
 * @Description 客户相关接口具体实现类
 * @createTime 2019年10月19日 10:26:00
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper,Member> implements IMemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public Member getByUsername(String usernameOrEmailOrPhone) {

        return memberMapper.getUserByName(usernameOrEmailOrPhone);
    }
}
