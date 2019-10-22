package org.mzkj.boot.system.modules.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mzkj.boot.system.modules.user.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @title 用户mapper
 * @description 
 * @author admin 
 * @updateTime 2019-10-22 11:03 
 * @throws 
 */
public interface MemberMapper extends BaseMapper<Member> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    public Member getUserByName(@Param("username") String username);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<Member> findByUsernameIn(List<String> usernameList);

}
