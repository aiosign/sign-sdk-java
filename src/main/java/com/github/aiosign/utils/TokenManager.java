package com.github.aiosign.utils;

import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.RevokeTokenRequest;
import com.github.aiosign.module.request.TokenRequest;
import com.github.aiosign.module.response.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationListener;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>TokenManager class.</p>
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
public class TokenManager {
    /**
     * 用于缓存map,key->appid,value->access_token
     */
    private static final Map<DefaultSignClient, String> accessTokens;

    /**
     * 利用有过期时间的map，token有效期为7200秒，这里设置为7000秒，7000秒后token自动过期
     */
    static {
        accessTokens = ExpiringMap.builder()
                .maxSize(100)
                .expiration(2, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .expirationListener(new TokenReloadListener())
                .variableExpiration()
                .build();
    }

    /**
     * 获取token
     *
     * @param signClient a {@link SignClient} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getToken(SignClient signClient) {
        DefaultSignClient defaultSignClient = (DefaultSignClient) signClient;
        String appId = defaultSignClient.getAppId();
        Assert.hasText(appId, "应用id不能为空");
        String token = accessTokens.get(defaultSignClient);
        if (!StringUtils.hasText(token)) {
            token = doGetToken(defaultSignClient);
            accessTokens.put(defaultSignClient, token);
        }
        return token;
    }

    /**
     * 发起请求获取token的值
     *
     * @param defaultSignClient
     * @return
     */
    private static String doGetToken(DefaultSignClient defaultSignClient) {
        TokenRequest tokenRequest = new TokenRequest(defaultSignClient.getAppId(), defaultSignClient.getAppSecret());
        TokenResponse response = defaultSignClient.execute(tokenRequest);
        if (response.isSuccess()) {
            return response.getData().getAccessToken();
        }
        return null;
    }

    /**
     * 清除原有的token
     *
     * @param signClient
     * @param token
     */
    private static void revokeToken(SignClient signClient, String token) {
        RevokeTokenRequest revokeTokenRequest = new RevokeTokenRequest(token);
        signClient.execute(revokeTokenRequest);
    }

    /**
     * token失效后重新获取
     */
    @Slf4j
    public static class TokenReloadListener implements ExpirationListener<DefaultSignClient, String> {

        /**
         * Called when a map entry expires.
         *
         * @param defaultSignClient Expired key
         * @param token             Expired value
         */
        @Override
        public void expired(DefaultSignClient defaultSignClient, String token) {
            log.debug("appid>>>{},token>>>{}即将过期,开始获取新的token", defaultSignClient, token);
            revokeToken(defaultSignClient, token);
            String refresh = doGetToken(defaultSignClient);
            if (StringUtils.hasText(refresh)) {
                log.debug("token刷新成功，应用id为{}", defaultSignClient.getAppId());
                accessTokens.put(defaultSignClient, refresh);
            }
        }
    }

}
