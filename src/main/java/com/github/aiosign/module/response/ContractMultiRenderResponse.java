package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 渲染v2返回参数
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/3/16 10:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractMultiRenderResponse extends AbstractSignResponse {

    private RenderModule data;

    @Data
    public static class RenderModule {
        /**
         * 合同Id
         */
        private String contractId;
        /**
         * 是否渲染成功
         */
        private boolean renderResult;
        /**
         * 合同页数
         */
        private Integer total;
        /**
         * 渲染信息
         */
        private String message;

        /**
         * 渲染详细数据
         */
        private List<RenderModule.RenderInfo> renderInfos;

        @Data
        public static class RenderInfo {
            /**
             * 每页渲染结果
             */
            private boolean renderResult;
            /**
             * 渲染信息
             */
            private String message;
            /**
             * 渲染页码
             */
            private Integer pageNum;
            /**
             * 文件内容base64
             */
            private String file;
        }
    }
}
