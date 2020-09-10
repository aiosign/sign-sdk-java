package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 查询绑定合同返回参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryBindContractResponse extends AbstractSignResponse {
    private List<QueryBindContractModule> data;

    /**
     * 查询绑定合同
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class QueryBindContractModule extends BaseSignObject {

        /**
         * id
         */
        private String id;

        /**
         * 开放平台合同ID
         */
        private String contractId;

        /**
         * 开放平台合同名字
         */
        private String contractName;

        /**
         * 开放平台合同创建时间
         */
        private String contracrCreateTime;

        /**
         * 开放平台合同状态
         */
        private String status;

        /**
         * 手机号
         */
        private String uniqueIdentifier;

        /**
         * 应用接口appId
         */
        private String appId;

        /**
         * 创建时间
         */
        private LocalDateTime createTime;

        /**
         * 更新时间
         */
        private LocalDateTime updateTime;
    }
}
