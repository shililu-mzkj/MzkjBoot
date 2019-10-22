package org.mzkj.boot.common.base;

import org.mzkj.boot.common.constant.CommonConstant;
import org.mzkj.boot.common.exception.BaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @title 公共返回封装
 * @description 
 * @author shililu
 * @updateTime 2019-10-22 10:58
 * @throws 
 */
@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功！";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private long code = 0;

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T data;

    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public CommonResult() {

    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResult<T> error500(String message) {
        this.message = message;
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    public CommonResult<T> success(String message) {
        this.message = message;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }


    public static CommonResult<Object> ok() {
        CommonResult<Object> r = new CommonResult<Object>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage("成功");
        return r;
    }

    public static CommonResult<Object> ok(String msg) {
        CommonResult<Object> r = new CommonResult<Object>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage(msg);
        return r;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(Status.SUCCESS.getCode(), message, data);
    }

    public static CommonResult<Object> ok(Object data) {
        CommonResult<Object> r = new CommonResult<Object>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setData(data);
        return r;
    }

    public static CommonResult<Object> error(String msg) {
        return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(Status.UNAUTHORIZED.getCode(), Status.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(Status.FORBIDDEN.getCode(), Status.FORBIDDEN.getMessage(), data);
    }

    public static CommonResult<Object> error(int code, String msg) {
        CommonResult<Object> r = new CommonResult<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link Status}
     * @return ApiResponse
     */
    public static CommonResult ofStatus(Status status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return ApiResponse
     */
    public static CommonResult of(Integer code, String message, Object data) {
        return new CommonResult(code, message, data);
    }
    /**
     * 构造一个成功且不带数据的API返回
     *
     * @return ApiResponse
     */
    public static CommonResult ofSuccess() {
        return ofSuccess(null);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static CommonResult ofSuccess(Object data) {
        return ofStatus(Status.SUCCESS, data);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link IStatus}
     * @param data   返回数据
     * @return ApiResponse
     */
    public static CommonResult ofStatus(IStatus status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }


    /**
     * 构造一个异常的API返回
     *
     * @param t   异常
     * @param <T> {@link BaseException} 的子类
     * @return ApiResponse
     */
    public static <T extends BaseException> CommonResult ofException(T t) {
        return of(t.getCode(), t.getMessage(), t.getData());
    }
    /**
     * 无权限访问返回结果
     */
    public static CommonResult<Object> noauth(String msg) {
        return error(CommonConstant.SC_JEECG_NO_AUTHZ, msg);
    }
}
