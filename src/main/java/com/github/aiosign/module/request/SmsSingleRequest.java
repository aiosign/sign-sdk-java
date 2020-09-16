package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.enums.SmsType;
import com.github.aiosign.module.response.SmsSingleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @since 2020/8/10 15:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsSingleRequest extends AbstractSignRequest<SmsSingleResponse> {

    /**
     * 用户名称不能为空
     */
    private String userName;

    /**
     * 合同名称不能为空
     */
    private String contractName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 短信类型
     */
    private SmsType smsType;


    @Override
    @JsonIgnore
    public RequestInfo<SmsSingleResponse> getRequestInfo() {
        RequestInfo<SmsSingleResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/sms/single");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SmsSingleResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
