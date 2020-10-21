package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 根据用户、印章类型所有印章 响应参数
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SealInfosByUserOrTypeResponse extends AbstractSignResponse {

    private List<SealInfoModule> data;

    @Data
    public static class SealInfoModule {
        /**
         * 印章ID
         */
        private String sealId;
        /**
         * 印章名称
         */
        private String sealName;
        /**
         * 印章类型
         */
        private String sealType;
        /**
         * 印章规格
         */
        private String size;
        /**
         * 印章文件ID
         */
        private String fileId;
        /**
         * 文件状态 1 paas 2 私有 3 共有
         */
        private String fileResourceStatus;
        /**
         * 用户ID
         */
        private String userId;
        /**
         * 用户类型
         */
        private String userType;
        /**
         * 用户名称
         */
        private String userName;

    }
}
