package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 企业用户注册 与 证书申请
 *
 * @author 侯存路
 * @since 2020/6/16 9:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCompanyRegisterCertResponse extends AbstractSignResponse {

    /**
     * 证书相关
     */
    private Map<String, Object> data;


    /**
     * 用户id
     */
    private String userId;

}
