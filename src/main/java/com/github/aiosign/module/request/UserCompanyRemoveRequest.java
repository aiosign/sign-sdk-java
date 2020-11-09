package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserCompanyRemoveResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 企业用户注销 请求参数
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/9 17:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyRemoveRequest extends AbstractSignRequest<UserCompanyRemoveResponse> {
    /**
     * 用户ID
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<UserCompanyRemoveResponse> getRequestInfo() {
        RequestInfo<UserCompanyRemoveResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/user/company/remove");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(UserCompanyRemoveResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
