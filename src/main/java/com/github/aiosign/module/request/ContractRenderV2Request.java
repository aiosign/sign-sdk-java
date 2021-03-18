package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractRenderV2Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * v2合同渲染
 * @author Administrator
 * @version 1.0
 * @date 2021/3/16 10:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContractRenderV2Request extends AbstractSignRequest<ContractRenderV2Response> {
    /**
     * 合同id
     */
    private String contractId;

    @Override
    @JsonIgnore
    public RequestInfo<ContractRenderV2Response> getRequestInfo() {
        RequestInfo<ContractRenderV2Response> requestInfo=new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v2/contract/render");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractRenderV2Response.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
