package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 绑定合同返回参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractBindPhoneResponse extends AbstractSignResponse {
    private ContractBindPhoneModule data;

    /**
     * 绑定合同
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ContractBindPhoneModule extends BaseSignObject {
        /**
         * 合同id
         */
        private String contractId;
    }
}
