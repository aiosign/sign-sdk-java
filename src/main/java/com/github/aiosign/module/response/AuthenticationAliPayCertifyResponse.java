package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @since 2020/8/11 8:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationAliPayCertifyResponse extends AbstractSignResponse {


    private AuthenticationAliPayCertifyModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationAliPayCertifyModel extends BaseSignObject {

        /**
         * 证书id
         */
        private String certifyId;

        /**
         * 路径
         */
        private String url;
    }


}
