package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserPersonalUnLockResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 个人用户锁定或解锁返回结果
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalUnLockRequest extends AbstractSignRequest<UserPersonalUnLockResponse> {

    /**
     * 用户id
     */
    private String userId;

    @Override
    @JsonIgnore
    public RequestInfo<UserPersonalUnLockResponse> getRequestInfo() {
        RequestInfo<UserPersonalUnLockResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("user/personal/unlock");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(UserPersonalUnLockResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
