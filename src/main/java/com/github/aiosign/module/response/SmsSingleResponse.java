package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @since 2020/8/10 15:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsSingleResponse extends AbstractSignResponse {


    private SmsModule data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SmsModule extends BaseSignObject {

        private String phone;

        private boolean result;
    }

}
