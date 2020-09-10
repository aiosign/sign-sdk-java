package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SealBatchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 印章唯一标识,以逗号隔开
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealRemoveRequest extends AbstractSignRequest<SealBatchResponse> {


    /**
     * 印章id
     */
    private String sealId;

    @Override
    @JsonIgnore
    public RequestInfo<SealBatchResponse> getRequestInfo() {
        RequestInfo<SealBatchResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("seal/remove");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SealBatchResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
