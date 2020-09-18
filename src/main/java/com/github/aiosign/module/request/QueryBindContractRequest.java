package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.QueryBindContractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 查询合同绑定手机号 请求参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryBindContractRequest extends AbstractSignRequest<QueryBindContractResponse> {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 合同名称
     */
    private String contractName;

    @Override
    @JsonIgnore
    public RequestInfo<QueryBindContractResponse> getRequestInfo() {
        RequestInfo<QueryBindContractResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/bind_contract/query_bind_contract");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(QueryBindContractResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
