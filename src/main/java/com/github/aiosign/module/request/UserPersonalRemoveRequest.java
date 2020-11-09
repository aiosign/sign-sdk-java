package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserPersonalRemoveResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 个人用户注销
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/9 17:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalRemoveRequest extends AbstractSignRequest<UserPersonalRemoveResponse> {
    /**
     * 用户ID
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<UserPersonalRemoveResponse> getRequestInfo() {
        RequestInfo<UserPersonalRemoveResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/user/personal/remove");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(UserPersonalRemoveResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
