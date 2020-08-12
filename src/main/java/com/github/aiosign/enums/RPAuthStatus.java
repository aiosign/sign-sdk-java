package com.github.aiosign.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 人脸识别状态
 */
public enum RPAuthStatus {
    PASS(1, "认证通过"),
    FAIL2(2, "身份证照片模糊、光线问题造成字体无法识别、身份证照片信息与需认证的身份证姓名不一致、提交的照片为非身份证照片。"),
    FAIL3(3, "身份证照片模糊、光线问题造成字体无法识别、身份证照片信息与需认证的身份证号码不一致、提交的照片为非身份证照片。"),
    FAIL4(4, "身份证照片上的有效期已过期（或即将过期）。"),
    FAIL5(5, "人脸与身份证头像不一致。"),
    FAIL6(6, "人脸与公安网照片不一致。"),
    FAIL7(7, "提交的身份证照片非身份证原照片、未提交有效身份证照片。"),
    FAIL8(8, "实名校验不通过"),
    FAIL9(9, "非账户本人操作。"),
    FAIL11(11, "公安网照片缺失、公安网照片格式错误、公安网照片未找到人脸。"),
    FAIL12(12, "公安网系统异常，无法进行照片比对等可能。");

    @JsonValue
    private int status;

    private String description;

    RPAuthStatus(int status, String description) {
        this.description = description;
        this.status = status;
    }

    /**
     * 根据状态获取枚举
     *
     * @param status
     * @return
     */
    public static RPAuthStatus of(int status) {
        for (RPAuthStatus rpAuthStatus : values()) {
            if (rpAuthStatus.getStatus() == status) {
                return rpAuthStatus;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
