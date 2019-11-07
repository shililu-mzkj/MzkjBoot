package org.mzkj.boot.system.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.mzkj.boot.system.dto.MzkjUser;
import org.mzkj.boot.system.util.SecurityUtils;

import java.time.LocalDateTime;

/**
 * @author shililu
 * @version 1.0.0
 * @ClassName MyMetaObjectHandler.java
 * @Description mybatis-plus 填充自動
 * @createTime 2019年11月06日 14:36:00
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		MzkjUser mzkjUser= SecurityUtils.getUser();
		this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
		this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
		this.setFieldValByName("createBy", mzkjUser.getId(),metaObject);
		this.setFieldValByName("updateBy", mzkjUser.getId(),metaObject);

	}

	@Override
	public void updateFill(MetaObject metaObject) {
		MzkjUser mzkjUser= SecurityUtils.getUser();
		this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
		this.setFieldValByName("updateBy", mzkjUser.getId(),metaObject);
	}
}
