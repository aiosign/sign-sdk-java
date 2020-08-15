package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板填充返回参数
 * @author modificial
 * @since 2020/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateFillResponse extends AbstractSignResponse {
    /**
     * 模板填充对象
     */
    private TemplateFillModule data;

    /**
     * 模板填充返回值
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TemplateFillModule extends BaseSignObject {
        /**
         * 文件id
         */
        private String fileId;
        /**
         * 文件名字
         */
        private String fileName;
    }
}
