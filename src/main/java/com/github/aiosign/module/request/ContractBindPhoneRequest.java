package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractBindPhoneResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * 合同绑定手机号 请求参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractBindPhoneRequest extends AbstractSignRequest<ContractBindPhoneResponse> {
    /**
     * 合同ID
     */
    private String contractId;

    private List<BindInfo> params;

    @Data
    public static class BindInfo implements Serializable {
        /**
         * 手机号
         */
        private String phone;
    }

    @Override
    @JsonIgnore
    public RequestInfo<ContractBindPhoneResponse> getRequestInfo() {
        RequestInfo<ContractBindPhoneResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("bind_contract/bind_contract_phone");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractBindPhoneResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
