package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * v2一步签署返回参数
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/3/3 9:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DirectSignV2Response extends AbstractSignResponse {
    /**
     * v2一步签署返回参数
     */
    private DirectSignV2Module data;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class DirectSignV2Module extends BaseSignObject {
        /**
         * 签署结果
         */
        private Boolean signState;
        /**
         * 签署信息
         */
        private String message;
        /**
         * 签署文件ID
         */
        private String fileId;
        /**
         * 签署文件内容base64
         */
        private String signFile;
    }
}
