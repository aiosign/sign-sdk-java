package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 获取个人用户ID以及注册  响应值
 * @author Administrator
 * @version 1.0
 * @date 2021/4/13 14:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPersonalRegisterOrQueryResponse extends AbstractSignResponse {
    private UserModule data;

    /**
     * 用户信息
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserModule extends BaseSignObject {
        /**
         * 用户ID
         */
        private String userId;

        /**
         * 异步申请证书预处理ID
         */
        private String prepareId;

        /**
         * 证书信息
         */
        private UserPersonalRegisterOrQueryResponse.UserModule.CertInfo certInfo;

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
