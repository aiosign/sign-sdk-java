package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SealQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 印章id
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealIdentityRequest extends AbstractSignRequest<SealQueryResponse> {

    /**
     * 印章id
     */
    private String sealId;

    @Override
    @JsonIgnore
    public RequestInfo<SealQueryResponse> getRequestInfo() {
        RequestInfo<SealQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/seal/query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SealQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
