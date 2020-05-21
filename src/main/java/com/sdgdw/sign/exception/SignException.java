package com.sdgdw.sign.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdgdw.sign.utils.ObjectMapperHolder;
import com.sdgdw.sign.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 自定义异常，当状态不是正常时抛出此异常
 * @author modificial
 * @description
 * @since 2020/5/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignException extends RuntimeException{
    /**
     * 状态码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    public SignException(String code,String message){
        super(message);
        this.code=code;
        this.message=message;
    }

    public SignException(String message){
        super(message);
    }
}
