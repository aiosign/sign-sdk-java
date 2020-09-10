package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserInfoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户详情信息返回
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoRequest extends AbstractSignRequest<UserInfoResponse> {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 返回请求的必要参数信息
     *
     * @return
     */
    @Override
    @JsonIgnore
    public RequestInfo<UserInfoResponse> getRequestInfo() {
        RequestInfo<UserInfoResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("user/personal/userinfo");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(UserInfoResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
