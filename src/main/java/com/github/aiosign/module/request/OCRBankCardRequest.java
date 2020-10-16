package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.OCRBankCardResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 银行卡OCR识别 请求参数
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OCRBankCardRequest extends AbstractSignRequest<OCRBankCardResponse> {

    /**
     * base64编码银行卡图片
     */
    private String image;

    @Override
    @JsonIgnore
    public RequestInfo<OCRBankCardResponse> getRequestInfo() {
        RequestInfo<OCRBankCardResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/authentication/OCR-bank-card");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(OCRBankCardResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
