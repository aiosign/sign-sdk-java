package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.AuthenticationEncryQueryResponse;
import com.github.aiosign.module.response.SmsAuthCodeResponse;
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
public class AuthenticationEncryQueryRequest extends AbstractSignRequest<AuthenticationEncryQueryResponse> {


    /**
     * 名字
     */
    private String name;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phone;



    @Override
    @JsonIgnore
    public RequestInfo<AuthenticationEncryQueryResponse> getRequestInfo() {
        RequestInfo<AuthenticationEncryQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/encryQuery");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(AuthenticationEncryQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
