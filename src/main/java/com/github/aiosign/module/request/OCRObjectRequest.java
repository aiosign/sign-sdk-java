package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.OCRObjectResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 证件识别 请求参数
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OCRObjectRequest extends AbstractSignRequest<OCRObjectResponse> {

    /**
     * 文件名称不可为空
     */
    private String fileName;
    /**
     * base64 文件不可为空
     */
    private String fileBase64;
    /**
     * 识别的文件类型
     */
    private String cardType;

    @Override
    @JsonIgnore
    public RequestInfo<OCRObjectResponse> getRequestInfo() {
        RequestInfo<OCRObjectResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/authentication/OCR-Object");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(OCRObjectResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
