package com.goufaning.mall.common.result;

/**
 * 枚举了一些常用API的操作码
 *
 * @author goufn
 * @version V1.0
 * @date 2020-07-09 14:14
 */
public enum ResultCode implements IErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    FAILED(-1, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    BAD_ARGUEMENT(401, "参数不对"),
    BAD_ARGUEMENT_VALUE(402, "参数值不对"),
    FORBIDDEN(403, "没有相关权限"),
    UN_LOGIN(501, "请登录"),
    SERIOUS(502, "系统内部错误"),
    UN_SUPPORT(503, "业务不支持"),
    UPDATE_DATA_EXPIRED(504, "更新数据已经失效"),
    UPDATE_DATA_FAILED(505, "更新数据失败"),
    UNAUTHORIZED(506, "无操作权限"),

    ;
    private int code;
    private String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
