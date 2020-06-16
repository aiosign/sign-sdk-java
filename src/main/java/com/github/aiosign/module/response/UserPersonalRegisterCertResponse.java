package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 个人用户注册 与 用户的证书申请
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPersonalRegisterCertResponse extends AbstractSignResponse {


    /**
     * 证书相关
     */
    private Map<String, Object> data;


    /**
     * 用户id
     */
    private String userId;


}
