package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.enums.SmsType;
import com.github.aiosign.module.response.SmsBatchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsBatchRequest extends AbstractSignRequest<SmsBatchResponse> {

    private List<SendInfo> params;

    private SmsType smsType;

    @Override
    @JsonIgnore
    public RequestInfo<SmsBatchResponse> getRequestInfo() {
        RequestInfo<SmsBatchResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/sms/batch");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SmsBatchResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendInfo implements Serializable {

        private String phone;

        private String userName;

        private String contractName;
    }


}
