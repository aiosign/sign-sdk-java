package com.github.aiosign.module.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserPersonalRegisterOrQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取个人用户ID以及注册  请求参数
 * @author Administrator
 * @version 1.0
 * @date 2021/4/13 14:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPersonalRegisterOrQueryRequest extends AbstractSignRequest<UserPersonalRegisterOrQueryResponse> {
    /**
     * 用户名（必填）
     */
    private String userName;

    /**
     * 地区编码（必填）
     */
    private String areaCode;

    /**
     * 手机号（必填）
     */
    private String phone;

    /**
     * 证件类型（必填）
     */
    private String idType;

    /**
     * 证件号（必填）
     */
    private String idNumber;

    /**
     * 邮箱地址（非必填）
     */
    private String mail;

    /**
     * 证书申请方式，0不申请（默认）;1同步申请;2异步申请
     */
    private Integer certApplyType;



    @Override
    @JsonIgnore
    public RequestInfo<UserPersonalRegisterOrQueryResponse> getRequestInfo() {
        RequestInfo<UserPersonalRegisterOrQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/user/personal/register-or-query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(UserPersonalRegisterOrQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
