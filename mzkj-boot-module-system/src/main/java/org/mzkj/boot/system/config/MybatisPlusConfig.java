package org.mzkj.boot.system.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title mybatis-plus配置
 * @description 
 * @author shililu
 * @updateTime 2019-10-22 11:01 
 * @throws 
 */
@Configuration
@MapperScan(value={"org.mzkj.boot.system.modules.**.mapper*"})
public class MybatisPlusConfig {

   /**
    * @title 分页插件
    * @description 
    * @author admin 
    * @updateTime 2019-10-22 11:08 
    * @throws 
    */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    
   
}
