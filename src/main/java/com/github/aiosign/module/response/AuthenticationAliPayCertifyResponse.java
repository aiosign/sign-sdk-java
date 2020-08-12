package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import com.github.aiosign.enums.CertifyType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author 侯存路
 * @description
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
         * uuid
         */
        private String id;

        /**
         * 证书id
         */
        private String certifyId;

        /**
         * 路径
         */
        private String url;
        /**
         * 应用ID
         */
        private String appId;
        /**
         * 用户身份证证件号码
         */
        private String idCardNum;
        /**
         * 用户名称
         */
        private String name;
        /**
         * 认证模式
         */
        private CertifyType certifyType;
        /**
         * 认证结果
         */
        private String result;


        /**
         * 检测时间
         */
        private LocalDateTime createTime;

        private LocalDateTime updateTime;
    }


}
