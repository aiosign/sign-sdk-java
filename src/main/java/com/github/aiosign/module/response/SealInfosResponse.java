package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取用户所有印章 响应参数
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SealInfosResponse extends AbstractSignResponse {

    private List<SealInfoModule> data;

    /**
     * 印章信息
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SealInfoModule extends BaseSignObject {
        /**
         * 印章id
         */
        private String sealId;

        /**
         * 印章名字
         */
        private String sealName;

        /**
         * 印章类型
         */
        private String sealType;

        /**
         * 印章文件id
         */
        private String fileId;

        /**
         * 印章规格
         */
        private String size;

        /**
         * 印章状态
         */
        private String status;
    }
}
