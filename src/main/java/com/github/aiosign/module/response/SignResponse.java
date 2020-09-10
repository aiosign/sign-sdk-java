package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 单次签章返回结果
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignResponse extends AbstractSignResponse {

    private SignModule data;

    /**
     * 模本签名参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SignModule extends BaseSignObject {
        /**
         * 签章完成后 hash 值
         */
        public String hash;
        /**
         * 签章id
         */
        private String signId;
        /**
         * 文件id
         */
        private String fileId;
        /**
         * 签章状态
         */
        private boolean signState;
        private List<EventCert> eventCerts;

    }

    @Data
    public static class EventCert {
        private String userId;
        private String certId;
    }
}
