package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SmsAuthCodeResponse;
import com.github.aiosign.module.response.SmsBatchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsAuthCodeRequest extends AbstractSignRequest<SmsAuthCodeResponse> {


    private List<Phone> phones;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
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
         * 验证码有效时间(秒)
         */
        private Integer expireTime;
    }


    @Override
    @JsonIgnore
    public RequestInfo<SmsAuthCodeResponse> getRequestInfo() {
        RequestInfo<SmsAuthCodeResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("sms/auth_code");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SmsAuthCodeResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
