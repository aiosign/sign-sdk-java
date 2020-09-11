package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsValidAuthCodeResponse extends AbstractSignResponse {


    private SmsValidAuthCodeModel data;


    @Data
    public static class SmsValidAuthCodeModel {

        /**
         * 手机号
         */
        private String phone;

        /**
         * 验证结果
         */
        private Boolean status;

    }


}
