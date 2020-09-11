package com.github.aiosign.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 侯存路
 * @since 2020/8/11 16:33
 */
public enum CertifyType {

    /**
     *
     */
    FACE("FACE", "多因子人脸认证"),
    CERT_PHOTO("CERT_PHOTO", "多因子证照认证"),
    CERT_PHOTO_FACE("CERT_PHOTO_FACE", "多因子证照和人脸认证"),
    SMART_FACE("SMART_FACE", "多因子快捷认证");

    @JsonValue
    private String code;
    private String typeStr;

    CertifyType(String code, String typeStr) {
        this.code = code;
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public String getCode() {
        return code;
    }


}
