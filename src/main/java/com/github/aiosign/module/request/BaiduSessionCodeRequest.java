package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.BaiduSessionCodeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 企业打款认证
 *
 * @author modificial
 * @since 2020/4/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaiduSessionCodeRequest extends AbstractSignRequest<BaiduSessionCodeResponse> {


    @Override
    @JsonIgnore
    public RequestInfo<BaiduSessionCodeResponse> getRequestInfo() {
        RequestInfo<BaiduSessionCodeResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/baidu/session-code");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(BaiduSessionCodeResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
