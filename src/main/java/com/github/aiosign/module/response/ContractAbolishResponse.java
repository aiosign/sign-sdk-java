package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合同废除返回参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractAbolishResponse extends AbstractSignResponse {

    private ContractAbolishModule data;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ContractAbolishModule extends BaseSignObject {
        /**
         * 签署ID
         */
        private String signId;

        /**
         * 文件ID
         */
        private boolean fileId;
        /**
         * 删除的结果 true操作成功 false操作失败
         */
        private boolean result;
    }

}
