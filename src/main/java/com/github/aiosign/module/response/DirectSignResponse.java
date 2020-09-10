package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 一步签署返回参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DirectSignResponse extends AbstractSignResponse {

    private DirectSignModule data;

    /**
     * 一步签署
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class DirectSignModule extends BaseSignObject {
        /**
         * 签章id
         */
        private String signId;
        /**
         * 文件id
         */
        private String contractFile;
        /**
         * 签章状态
         */
        private boolean signState;
        /**
         * 签章完成后 hash 值
         */
        private String hash;
        /**
         * 签署时间
         */
        private LocalDateTime signTime;
        /**
         * 签署人
         */
        private String signer;
        /**
         * 证书序列号
         */
        private String certSn;
        /**
         * 证书颁发者
         */
        private String certIssuer;
        /**
         * 错误信息
         */
        private String message;
    }
}
