package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.CertRenewalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 证书续期 请求参数
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertRenewalRequest extends AbstractSignRequest<CertRenewalResponse> {
    /**
     * 用户id
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<CertRenewalResponse> getRequestInfo() {
        RequestInfo<CertRenewalResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/cert/renewal");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(CertRenewalResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
