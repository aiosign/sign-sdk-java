package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 合同id集合
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractIdentityListRequest extends AbstractSignRequest<ContractDeleteResponse> {

    /**
     * 合同id
     */
    private String contractId;

    @Override
    @JsonIgnore
    public RequestInfo<ContractDeleteResponse> getRequestInfo() {
        RequestInfo<ContractDeleteResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("contract/remove");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractDeleteResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
