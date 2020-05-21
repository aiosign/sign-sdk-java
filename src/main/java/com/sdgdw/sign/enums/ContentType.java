package com.sdgdw.sign.enums;

import com.sdgdw.sign.utils.StringUtils;

/**
 * 请求头类型
 * @author modificial
 * @description
 * @since 2020/5/11
 */
public enum ContentType {
    /**
     * query类型
     */
    FORM_URLENCODED("application/x-www-form-urlencoded"),
    /**
     * 表单类型
     */
    MULTIPART("multipart/form-data"),
    /**
     * json类型
     */
    JSON("application/json"),
    /**
     * xml类型
     */
    XML("application/xml");
    private String value;

    private ContentType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static boolean isDefault(String contentType) {
        return null == contentType || isFormUrlEncoed(contentType);
    }

    public static boolean isFormUrlEncoed(String contentType) {
        return FORM_URLENCODED.toString().equals(contentType);
    }

    public static ContentType get(String body) {
        ContentType contentType = null;
        if (StringUtils.hasText(body)) {
            char firstChar = body.charAt(0);
            switch(firstChar) {
                case '<':
                    contentType = XML;
                    break;
                case '[':
                case '{':
                    contentType = JSON;
            }
        }

        return contentType;
    }
}
