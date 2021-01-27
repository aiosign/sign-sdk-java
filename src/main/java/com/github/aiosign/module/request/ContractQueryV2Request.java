package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractQueryResponse;
import com.github.aiosign.module.response.ContractQueryV2Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * v2 合同查询
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/1/25 17:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractQueryV2Request extends AbstractSignRequest<ContractQueryV2Response> {

    /**
     * 合同id
     */
    private String contractId;

    @Override
    @JsonIgnore
    public RequestInfo<ContractQueryV2Response> getRequestInfo() {
        RequestInfo<ContractQueryV2Response> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v2/contract/query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractQueryV2Response.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
