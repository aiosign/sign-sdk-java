package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ChinaPayEnterprisePayValidResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 企业打款认证
 *
 * @author modificial
 * @since 2020/4/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChinaPayEnterprisePayValidReq extends AbstractSignRequest<ChinaPayEnterprisePayValidResponse> {

    /**
     * 企业银行账户
     */
    private String accountNo;

    /**
     * 打款流水Id
     */
    private String orderId;

    /**
     * 金额（分）
     */
    private String amount;


    @Override
    @JsonIgnore
    public RequestInfo<ChinaPayEnterprisePayValidResponse> getRequestInfo() {
        RequestInfo<ChinaPayEnterprisePayValidResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/enterprise-pay-valid");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ChinaPayEnterprisePayValidResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
