package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractMultiRenderResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多页渲染合同
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/3/16 10:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractMultiRenderRequest extends AbstractSignRequest<ContractMultiRenderResponse> {
    /**
     * 合同id
     */
    private String contractId;
    /**
     * 渲染页码，以","分隔
     */
    private String pageNums;

    @Override
    @JsonIgnore
    public RequestInfo<ContractMultiRenderResponse> getRequestInfo() {
        RequestInfo<ContractMultiRenderResponse> requestInfo=new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/contract/multi_page_render");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractMultiRenderResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
