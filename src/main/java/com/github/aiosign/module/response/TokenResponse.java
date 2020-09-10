package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author modificial
 * @since 2020/5/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TokenResponse extends AbstractSignResponse {
    /**
     * 详细数据
     */
    private TokenModule data;

    /**
     * token详细数据
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TokenModule extends BaseSignObject {
        /**
         * 用户令牌
         */
        private String accessToken;
        /**
         * 授权类型
         */
        private String scope;
        /**
         * token类型
         */
        private String tokenType;
        /**
         * 过期时间
         */
        private String expiresIn;
    }
}
