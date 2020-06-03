package com.github.aiosign.enums;

import com.github.aiosign.utils.StringUtils;

/**
 * 请求头类型
 *
 * @author modificial
 * @version $Id: $Id
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

    ContentType(String value) {
        this.value = value;
    }

    /**
     * <p>isDefault.</p>
     *
     * @param contentType a {@link java.lang.String} object.
     * @return a boolean.
     */
    public static boolean isDefault(String contentType) {
        return null == contentType || isFormUrlEncoed(contentType);
    }

    /**
     * <p>isFormUrlEncoed.</p>
     *
     * @param contentType a {@link java.lang.String} object.
     * @return a boolean.
     */
    public static boolean isFormUrlEncoed(String contentType) {
        return FORM_URLENCODED.toString().equals(contentType);
    }

    /**
     * <p>get.</p>
     *
     * @param body a {@link java.lang.String} object.
     * @return a {@link ContentType} object.
     */
    public static ContentType get(String body) {
        ContentType contentType = null;
        if (StringUtils.hasText(body)) {
            char firstChar = body.charAt(0);
            if (firstChar == '<') {
                contentType = XML;
            } else {
                contentType = JSON;
            }
        }

        return contentType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
