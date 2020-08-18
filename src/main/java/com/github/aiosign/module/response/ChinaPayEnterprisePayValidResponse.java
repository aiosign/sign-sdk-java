package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/18 9:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChinaPayEnterprisePayValidResponse extends AbstractSignResponse {


    private ChinaPayEnterprisePay data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ChinaPayEnterprisePay extends BaseSignObject {

        /**
         * 打款结果
         * 1:成功
         * 2:进行中
         * 3:失败
         */
        private int res;

        /**
         * 企业打款验证响应信息
         */
        private String message;

        /**
         * 企业银行卡号
         */
        private String accountNo;
    }
}
