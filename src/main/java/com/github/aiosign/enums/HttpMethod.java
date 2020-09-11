package com.github.aiosign.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求方法类型
 *
 * @author modificial
 * @version $Id: $Id
 */
public enum HttpMethod {
    /**
     * 请求方法
     */

    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;


    /**
     * Constant <code>MAPPINGS</code>
     */
    private static final Map<String, HttpMethod> MAPPINGS = new HashMap<>(16);

    static {
        for (HttpMethod httpMethod : values()) {
            MAPPINGS.put(httpMethod.name(), httpMethod);
        }
    }


    /**
     * 字符串转为枚举类型
     *
     * @param method 请求方法
     * @return a {@link HttpMethod} object.
     */
    public static HttpMethod resolve(String method) {
        return (method != null ? MAPPINGS.get(method) : null);
    }


    /**
     * 给定的方法是否匹配
     *
     * @param method 请求方法
     * @return a boolean.
     */
    public boolean matches(String method) {
        return (this == resolve(method));
    }

}
