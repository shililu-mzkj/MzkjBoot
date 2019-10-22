package org.mzkj.boot.system.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mzkj.boot.system.modules.user.model.Member;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName IMemberService.java
 * @Description 客户相关接口
 * @createTime 2019年10月19日 10:25:00
 */
public interface IMemberService extends IService<Member> {
    /**
     * 根据用户名获取会员
     */
    Member getByUsername(String usernameOrEmailOrPhone);
}
