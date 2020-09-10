package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.EnterprisePayResponse;
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
public class EnterprisePayReq extends AbstractSignRequest<EnterprisePayResponse> {

    /**
     * 企业代码类型,1：社会统一代码，2：工商注册号
     */
    private String keyType;
    /**
     * 企业代码
     */
    private String key;
    /**
     * 企业名称
     */
    private String name;
    /**
     * 法人名称
     */
    private String usrName;
    /**
     * 企业银行账户
     */
    private String accountNo;
    /**
     * 企业开户行
     */
    private String accountBank;
    /**
     * 企业开户行所在省份
     */
    private String accountProvince;
    /**
     * 企业开户行所在城市
     */
    private String accountCity;


    @Override
    @JsonIgnore
    public RequestInfo<EnterprisePayResponse> getRequestInfo() {
        RequestInfo<EnterprisePayResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/enterprise-pay");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(EnterprisePayResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
