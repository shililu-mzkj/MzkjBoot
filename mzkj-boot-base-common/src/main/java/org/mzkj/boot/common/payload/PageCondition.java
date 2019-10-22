package org.mzkj.boot.common.payload;

import lombok.Data;

/**
 * @title 分页请求参数
 * @description 
 * @author shililu
 * @updateTime 2019-10-22 10:59 
 * @throws 
 */
@Data
public class PageCondition {
    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
