package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import com.github.aiosign.bean.ContractInfo;
import com.github.aiosign.enums.EnterprisePayStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 企业打款校验
 *
 * @author modificial
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EnterprisePayResponse extends AbstractSignResponse {


    private EnterprisePayModule data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class EnterprisePayModule extends BaseSignObject {

        /**
         * 打款结果
         * 1:成功
         * 2:进行中
         * 3:失败
         */
        private int res;
        /**
         * 打款响应信息
         */
        private String message;
        /**
         * 打款流水Id
         */
        private String orderId;
        /**
         * 打款银行账户
         */
        private String accountNo;

    }
}
