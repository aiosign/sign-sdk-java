package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SignCheckResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 文件id
 * @author modificial
 * @since 2020/4/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileIdentityRequest extends AbstractSignRequest<SignCheckResponse> {
    /**
     * 文件id
     */
    private String fileId;

    @Override
    @JsonIgnore
    public RequestInfo<SignCheckResponse> getRequestInfo() {
        RequestInfo<SignCheckResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("sign/check/common");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignCheckResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
