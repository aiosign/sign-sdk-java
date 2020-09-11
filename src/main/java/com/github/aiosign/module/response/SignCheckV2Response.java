package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 验签返回参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignCheckV2Response extends AbstractSignResponse implements Serializable {
    private SignCheckModule data;

    @Data
    public static class SignCheckModule {
        /**
         * 验证信息
         */
        private String message;

        /**
         * 验签结果
         */
        private Boolean result;
        /**
         * 详细验签结果
         */
        private List<SignCheckResult> signCheckResults;

        /**
         * 验证签名的详细信息
         */
        @Data
        public static class SignCheckResult implements Serializable {
            /**
             * 签章时间
             */
            private String signTime;
            /**
             * 签章页数
             */
            private Integer pageNumber;
            /**
             * 时间戳
             */
            private String timestamp;
            /**
             * 校验结果
             */
            private Boolean result;
            /**
             * 签名人
             */
            private String signer;
            /**
             * 证书序列号
             */
            private String certSn;
            /**
             * 签章主题
             */
            private String theme;
            /**
             * 颁发机构
             */
            private String issuer;
        }
    }

}
