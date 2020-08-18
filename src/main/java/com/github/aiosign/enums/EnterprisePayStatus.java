package com.github.aiosign.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnterprisePayStatus {

    FAIL(3, "失败"),

    /**
     * 进行中
     */
    PROGRESSING(2, "进行中"),
    /**
     * 正常
     */
    SUCCESS(1, "成功");

    @JsonValue
    private int status;

    private String description;

    EnterprisePayStatus(int status, String description) {
        this.description = description;
        this.status = status;
    }

    EnterprisePayStatus(int status) {
        this.status = status;
    }

    /**
     * 根据状态获取枚举
     *
     * @param status
     * @return
     */
    public static EnterprisePayStatus of(int status) {
        for (EnterprisePayStatus dataStatus : values()) {
            if (dataStatus.getStatus() == status) {
                return dataStatus;
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
