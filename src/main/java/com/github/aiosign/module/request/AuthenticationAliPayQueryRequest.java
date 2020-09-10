package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.AuthenticationAliPayQueryResponse;
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
public class AuthenticationAliPayQueryRequest extends AbstractSignRequest<AuthenticationAliPayQueryResponse> {


    private String certiftyId;


    @Override
    @JsonIgnore
    public RequestInfo<AuthenticationAliPayQueryResponse> getRequestInfo() {
        RequestInfo<AuthenticationAliPayQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/alipay-certify-query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(AuthenticationAliPayQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
