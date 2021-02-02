package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SignLogQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 签署日志查询
 * @author Administrator
 * @version 1.0
 * @date 2021/2/2 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignLogQueryRequest  extends AbstractSignRequest<SignLogQueryResponse> {

    /**
     * 签署ID
     */
    private String signId;

    @Override
    @JsonIgnore
    public RequestInfo<SignLogQueryResponse> getRequestInfo() {
        RequestInfo<SignLogQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/sign/log/query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignLogQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
