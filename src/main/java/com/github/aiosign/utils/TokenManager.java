package com.github.aiosign.utils;

import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.TokenRequest;
import com.github.aiosign.module.response.TokenResponse;

/**
 * <p>TokenManager class.</p>
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
public class TokenManager {

    /**
     * 获取token
     *
     * @param signClient a {@link SignClient} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getToken(SignClient signClient) {
        return signClient.getToken();
    }

    
}
