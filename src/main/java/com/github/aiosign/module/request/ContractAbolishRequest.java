package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractAbolishResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 合同废除接口
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractAbolishRequest extends AbstractSignRequest<ContractAbolishResponse> {

    /**
     * 签署记录ID
     */
    private String signId;
    /**
     * 用户id
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<ContractAbolishResponse> getRequestInfo() {
        RequestInfo<ContractAbolishResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/contract/abolish");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractAbolishResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
