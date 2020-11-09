package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserCertResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *  同步申请证书请求参数
 * @author Administrator
 * @version 1.0
 * @date 2020/11/9 9:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertAyncApplyRequest extends AbstractSignRequest<UserCertResponse> {

    /**
     * 用户id
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<UserCertResponse> getRequestInfo() {
        RequestInfo<UserCertResponse> requestInfo=new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/cert/sync_apply");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(UserCertResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
