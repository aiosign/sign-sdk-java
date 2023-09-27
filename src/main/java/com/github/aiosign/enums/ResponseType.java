package com.github.aiosign.enums;

/**
 * WebUtils网络连接返回类型
 * 仅支持字符串和字节数组
 * @author Zhu Dunfeng
 * @date 2023/9/23 10:55
 */
public  enum ResponseType {

    /**
     * 返回为字符串类型
     */
    Str("java.lang.String", String.class),

    /**
     * 返回为字节数组类型
     */
    Byte("byte[]", byte[].class);

    private String typeReference;

    private Class typeClazz;

    ResponseType(String typeReference, Class typeClazz) {
        this.typeReference = typeReference;
        this.typeClazz = typeClazz;
    }

    public String getTypeReference() {
        return typeReference;
    }

    public Class getTypeClazz() {
        return typeClazz;
    }
}
