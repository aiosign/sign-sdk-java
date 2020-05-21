package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.SignCheckResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 条形码请求参数
 * @author modificial
 * @description
 * @since 2020/4/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarCodeRequest extends AbstractSignRequest<SignCheckResponse> {
    /**
     * 条形码请求参数
     */
    private String barCode;


    @Override
    @JsonIgnore
    public RequestInfo<SignCheckResponse> getRequestInfo() {
        RequestInfo<SignCheckResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("sign/check/barcode");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignCheckResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
