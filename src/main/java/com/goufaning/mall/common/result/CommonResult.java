package com.goufaning.mall.common.result;

import lombok.Data;

/**
 * 通用返回对象
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-09 14:18
 */
@Data
public class CommonResult<T> {
    /**
     * 返回码
     */
    private long errno;
    /**
     * 返回信息
     */
    private String errmsg;
    /**
     * 返回数据
     */
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(long errno, String errmsg, T data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonResult<T> success(String message, T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }


    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> error(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> error(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(int code, String message) {
        return new CommonResult<T>(code, message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> error() {
        return error(ResultCode.FAILED);
    }

    public static <T> CommonResult<T> badArgument() {
        return error(ResultCode.BAD_ARGUEMENT);
    }

    public static <T> CommonResult<T> badArgumentValue() {
        return error(ResultCode.BAD_ARGUEMENT_VALUE);
    }

    public static <T> CommonResult<T> unlogin() {
        return error(ResultCode.UN_LOGIN);
    }

    public static <T> CommonResult<T> serious() {
        return error(ResultCode.SERIOUS);
    }

    public static <T> CommonResult<T> unsupport() {
        return error(ResultCode.UN_SUPPORT);
    }

    public static <T> CommonResult<T> updatedDateExpired() {
        return error(ResultCode.UPDATE_DATA_EXPIRED);
    }

    public static <T> CommonResult<T> updatedDataFailed() {
        return error(ResultCode.UPDATE_DATA_FAILED);
    }

    public static <T> CommonResult<T> unauthorized() {
        return error(ResultCode.UNAUTHORIZED);
    }
}

