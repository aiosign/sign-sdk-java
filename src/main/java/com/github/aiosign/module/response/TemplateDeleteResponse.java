package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板删除返回参数
 *
 * @author modificial
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateDeleteResponse extends AbstractSignResponse {


    private TemplateDeleteModule data;

    /**
     * 模本签名参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class TemplateDeleteModule extends BaseSignObject {
        /**
         * 模板id
         */
        private String templateId;

        /**
         * 删除的结果 true操作成功 false操作失败
         */
        private boolean result;
    }

}
