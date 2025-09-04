package com.aurora.enums;

import lombok.Getter;

/**
 * @author AlbertYang
 * @date 2025/9/3 23:51
 */
@Getter
public enum GrpcCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "success"),
    /**
     * 部分成功
     */
    PARTIAL_SUCCESS(206, "partial success"),
    /**
     * 无效参数
     */
    INVALID_PARAM(400, "invalid param"),
    /**
     * 失败
     */
    FAIL(500, "fail");


    private int code;

    private String message;

     GrpcCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static GrpcCodeEnum getByCode(int code) {
        for (GrpcCodeEnum grpcCodeEnum : GrpcCodeEnum.values()) {
            if (grpcCodeEnum.getCode() == code) {
                return grpcCodeEnum;
            }
        }
        return null;
    }


}
