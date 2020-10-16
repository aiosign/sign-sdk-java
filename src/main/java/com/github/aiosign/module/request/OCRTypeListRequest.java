package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.OCRTypeListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 证件识别类型 请求参数
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OCRTypeListRequest extends AbstractSignRequest<OCRTypeListResponse> {

    @Override
    @JsonIgnore
    public RequestInfo<OCRTypeListResponse> getRequestInfo() {
        RequestInfo<OCRTypeListResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/authentication/OCR-type-list");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(OCRTypeListResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
