package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 合同id集合
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractIdentityRequest extends AbstractSignRequest<ContractQueryResponse> {

    /**
     * 合同id
     */
    private String contractId;


    @Override
    @JsonIgnore
    public RequestInfo<ContractQueryResponse> getRequestInfo() {
        RequestInfo<ContractQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("contract/query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
