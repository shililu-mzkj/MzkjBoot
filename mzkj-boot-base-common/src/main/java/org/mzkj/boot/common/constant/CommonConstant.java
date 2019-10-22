package org.mzkj.boot.common.constant;
/**
 * @title 常量类
 * @description 
 * @author shililu
 * @updateTime 2019-10-18 16:40 
 * @throws 
 */
public interface CommonConstant {

    /**
     * swagger token
     */
    public static String X_ACCESS_TOKEN = "X-Access-Token";
    public static String BD_DOC_TITLE = "BD 项目api接口文档";

    public static String BD_DOC_VERSION = "1.0";

    public static String BD_DOC_DES = "BD 项目api接口文档";


    /** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;

    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;

    /**
     * JWT 在 Redis 中保存的key前缀
     */
    String REDIS_JWT_KEY_PREFIX = "security:jwt:";

    /**
     * 星号
     */
    String SYMBOL_STAR = "*";

    /**
     * 默认当前页码
     */
    Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * 默认每页条数
     */
    Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 匿名用户 用户名
     */
    String ANONYMOUS_NAME = "匿名用户";


}
