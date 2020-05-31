package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>SignCheckResponse class.</p>
 *
 * @author yangyouwang
 * @version $Id: $Id
 * @since 2020/5/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignCheckResponse extends AbstractSignResponse {

    private SignCheckModule data;
    /**
     * 验签参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SignCheckModule extends BaseSignObject {
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
            private String  timestamp;
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
        }
    }
}
