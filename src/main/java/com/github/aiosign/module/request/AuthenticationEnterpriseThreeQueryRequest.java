package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.AuthenticationEnterpriseThreeQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEnterpriseThreeQueryRequest extends AbstractSignRequest<AuthenticationEnterpriseThreeQueryResponse> {


    /**
     * 注册号/统一社会信用代码
     */
    private String keyword;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 企业法人
     */
    private String opername;


    @Override
    @JsonIgnore
    public RequestInfo<AuthenticationEnterpriseThreeQueryResponse> getRequestInfo() {
        RequestInfo<AuthenticationEnterpriseThreeQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/enterprise-encry-query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(AuthenticationEnterpriseThreeQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
