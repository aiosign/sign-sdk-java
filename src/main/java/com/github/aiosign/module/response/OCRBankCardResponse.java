package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡OCR识别 响应参数
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OCRBankCardResponse extends AbstractSignResponse {
    private OCRBankCardModule data;

    @Data
    public static class OCRBankCardModule extends BaseSignObject {
        /**
         * 识别到的银行卡
         */
        private String bankCardNumber;
        /**
         * 有效期
         */
        private String validDate;
        /**
         * 银行名称
         */
        private String bankName;
        /**
         * 预留字段-忽略
         */
        private String bankCardType;
    }
}
