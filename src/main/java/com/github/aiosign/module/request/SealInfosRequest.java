package com.github.aiosign.module.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.github.aiosign.module.response.SealInfosResponse;

/**
 * 获取用户所有印章
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealInfosRequest extends AbstractSignRequest<SealInfosResponse> {

    /**
     * 用户ID
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<SealInfosResponse> getRequestInfo() {
        RequestInfo<SealInfosResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/seal/getSealInfos");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SealInfosResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
