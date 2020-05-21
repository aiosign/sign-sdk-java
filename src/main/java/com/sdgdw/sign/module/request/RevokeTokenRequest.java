package com.sdgdw.sign.module.request;

import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.RevokeTokenResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;

/**
 * @author modificial
 * @description
 * @since 2020/5/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevokeTokenRequest extends AbstractSignRequest<RevokeTokenResponse> {
    /**
     * 需要回收的token值
     */
    private String token;
    /**
     * 返回请求的必要参数信息
     *
     * @return
     */
    @Override
    public RequestInfo<RevokeTokenResponse> getRequestInfo() {
        RequestInfo<RevokeTokenResponse> requestInfo=new RequestInfo<>();
        requestInfo.setParams(Collections.singletonMap("token",token));
        requestInfo.setResponseType(RevokeTokenResponse.class);
        requestInfo.setNeedToken(false);
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setApiUri("oauth/revokeToken");
        requestInfo.setContentType(ContentType.FORM_URLENCODED);
        return requestInfo;
    }
}
