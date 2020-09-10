package com.github.aiosign.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ObjectMapper单例模式实现
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
public enum ObjectMapperHolder {
    /**
     * 单例
     */
    INSTANCE;
    /**
     * 时间格式
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final ObjectMapper objectMapper;

    /**
     * 构造单例的ObjectMapper
     */
    ObjectMapperHolder() {
        this.objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        objectMapper.registerModule(timeModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 获取单例的ObjectMapper
     *
     * @return a {@link com.fasterxml.jackson.databind.ObjectMapper} object.
     */
    public ObjectMapper getInstance() {
        return objectMapper;
    }
}
