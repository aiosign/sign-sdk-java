package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.ContractAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 合同id集合
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsyContractIdentityRequest extends AbstractSignRequest<ContractAddResponse> {

    /**
     * 合同id
     */
    private String contractId;

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public RequestInfo<ContractAddResponse> getRequestInfo() {
        RequestInfo<ContractAddResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("contract/render");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractAddResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
