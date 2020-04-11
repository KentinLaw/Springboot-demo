package com.kt.wms.model.result;

/**
 * @author kentinlaw
 * @version 1.0
 * @date 2020/4/2 16:11
 * @description Restful返回响应吗枚举类型
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败 
     */
    FAIL(400),

    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),

    /**
     * 无权限
     */
    PERMISSION_DENY(403),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }

}