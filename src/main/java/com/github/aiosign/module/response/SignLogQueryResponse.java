package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 签署日志 返回值
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/2/2 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignLogQueryResponse extends AbstractSignResponse {

    private SignLogModule data;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SignLogModule extends BaseSignObject {
        /**
         * 签署ID
         */
        private String signId;
        /**
         * 应用ID
         */
        private String appId;
        /**
         * 签署结果,1签署成功，0签署失败
         */
        private Integer result;
        /**
         * 签章类型：1,模板签章;3,关键字签章；4，普通签章;5,会签签章
         */
        private Integer signType;
        /**
         * 批量签章才有，批次号
         */
        private String batchId;
        /**
         * 只有关键字、普通、会签、扫码，才有合同id
         */
        private String contractId;
        /**
         * 只有单次、批量模板，才有模板id
         */
        private String templateId;
        /**
         * 签署完成后文件id
         */
        private String signFileId;
        /**
         * 签章用户
         */
        private String signUsers;
        /**
         * 创建时间
         */
        private LocalDateTime createTime;
        /**
         * 签署时间
         */
        private LocalDateTime signTime;
        /**
         * 签署详情
         */
        private List<SignLogDetail> signLogDetails;

        /**
         * 签署详情
         */
        @Data
        public static class SignLogDetail {

            /**
             * 用户ID
             */
            private String userId;

            /**
             * 用户名
             */
            private String userName;

            /**
             * 用户证件号
             */
            private String idNumber;

            /**
             * 用户类型 1 个人用户 2 企业用户
             */
            private Integer userType;

            /**
             * 证书ID
             */
            private String certId;

            /**
             * 证书类型，0 普通证书(长久证书) 1 事件证书
             */
            private Integer certType;

            /**
             * 印章ID
             */
            private String sealId;

            /**
             * 印章宽度
             */
            private Integer sealWidth;

            /**
             * 印章高度
             */
            private Integer sealHeight;

            /**
             * 印章位置信息json \n" +
             * "坐标签章时{page_index:*,x:*,y:*}\n" +
             * "关键字签章时{keyword:**,sign_all:true}\n" +
             * "模板签章时{sign_key:***}
             */
            private String sealSignParam;

            /**
             * 是否签署成功,1签署成功，0签署失败
             */
            private Integer signStatus;

            /**
             * 是否为图章记录,1是，0否
             */
            private Integer isPicture;

            /**
             * 文件的MD5
             */
            private String md5Hash;

            /**
             * 创建时间
             */
            private LocalDateTime createTime;

            /**
             * 签署时间
             */
            private LocalDateTime signTime;
        }
    }
}
