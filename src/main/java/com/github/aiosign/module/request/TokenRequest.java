package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.TokenResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 获取token的参数对象
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TokenRequest extends AbstractSignRequest<TokenResponse> {
    /**
     * 应用id
     */
    private String appId;
    /**
     * 应用秘钥
     */
    private String appSecret;
    /**
     * 授权类型固定为client_credentials
     */
    private String grantType = "client_credentials";

    /**
     * <p>Constructor for TokenRequest.</p>
     *
     * @param appId     a {@link java.lang.String} object.
     * @param appSecret a {@link java.lang.String} object.
     */
    public TokenRequest(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    /**
     * {@inheritDoc}
     * <p>
     * 返回请求的必要参数信息
     */
    @Override
    @JsonIgnore
    public RequestInfo<TokenResponse> getRequestInfo() {
        RequestInfo<TokenResponse> responseRequestInfo=new RequestInfo<>();
        responseRequestInfo.setApiUri("oauth/token");
        responseRequestInfo.setContentType(ContentType.JSON);
        responseRequestInfo.setMethod(HttpMethod.POST);
        responseRequestInfo.setNeedToken(false);
        responseRequestInfo.setResponseType(TokenResponse.class);
        responseRequestInfo.setRequestBody(this);
        return responseRequestInfo;
    }
}
