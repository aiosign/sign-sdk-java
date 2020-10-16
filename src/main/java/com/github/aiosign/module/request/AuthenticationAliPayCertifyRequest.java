package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.CertifyType;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.AuthenticationAliPayCertifyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @since 2020/8/11 16:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationAliPayCertifyRequest extends AbstractSignRequest<AuthenticationAliPayCertifyResponse> {


    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String idCardNum;

    /**
     * 认证模式
     */
    private CertifyType certifyType;


    @Override
    @JsonIgnore
    public RequestInfo<AuthenticationAliPayCertifyResponse> getRequestInfo() {
        RequestInfo<AuthenticationAliPayCertifyResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/authentication/alipay-certify-start");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(AuthenticationAliPayCertifyResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
