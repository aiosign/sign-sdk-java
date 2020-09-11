package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SmsAuthCodeResponse;
import com.github.aiosign.module.response.SmsValidAuthCodeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 9:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsValidAuthCodeRequest extends AbstractSignRequest<SmsValidAuthCodeResponse> {


    /**
     * 手机号不可为空
     */
    private String phone;

    /**
     * 手机号不可为空
     */
    private String uuid;

    /**
     * 验证码
     */
    private String authCode;



    @Override
    @JsonIgnore
    public RequestInfo<SmsValidAuthCodeResponse> getRequestInfo() {
        RequestInfo<SmsValidAuthCodeResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/sms/valid_auth_code");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SmsValidAuthCodeResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
