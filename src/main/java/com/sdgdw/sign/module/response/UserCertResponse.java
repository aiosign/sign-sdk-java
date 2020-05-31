package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户证书信息
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCertResponse extends AbstractSignResponse {

    private UserCertModule data;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserCertModule extends BaseSignObject {
        /**
         * 用户id
         */
        private String userId;
        /**
         * 申请证书状态
         */
        private boolean result;
        /**
         * 证书信息
         */
        private CertInfo certInfo;

        /**
         * 个人证书信息
         *
         * @author Administrator
         */
        @Data
        public static class CertInfo implements Serializable {

            /**
             * 证书id
             */
            private String certId;

            /**
             * 证书序列号
             */
            private String sn;

            /**
             * 证书名字
             */
            private String certName;

            /**
             * 证件号码
             */
            private String idNumber;

            /**
             * 颁发者
             */
            private String issuer;

            /**
             * 版本号
             */
            private Integer version;

            /**
             * 证书生效时间
             */
            private String startDate;

            /**
             * 证书失效时间
             */
            private String endDate;

            /**
             * 证书使用者名称
             */
            private String awardTo;

            /**
             * 证书状态
             */
            private String status;

        }
    }


}
