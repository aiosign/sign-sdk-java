package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsAuthCodeResponse extends AbstractSignResponse {


    private SmsAuthCode data;


    @Data
    public static class SmsAuthCode {


        private List<Phone> phones;

        /**
         * 批量签署对象信息
         */
        @Data
        public static class Phone {
            /**
             * 自定义Id
             */
            private String customId;
            /**
             * 手机号
             */
            private String phone;
            /**
             * 验证码Id
             */
            private String uuid;
            /**
             * 发送结果
             */
            private Boolean status;
        }

    }


}
