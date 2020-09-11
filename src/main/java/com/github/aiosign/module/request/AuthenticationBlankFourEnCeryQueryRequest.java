package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.AuthenticationBlankFourEnCeryQueryResponse;
import com.github.aiosign.module.response.AuthenticationEncryQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationBlankFourEnCeryQueryRequest extends AbstractSignRequest<AuthenticationBlankFourEnCeryQueryResponse> {

    /**
     * 姓名
     */
    private String realname;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 银行卡
     */
    private String bankcard;
    /**
     * 手机号码
     */
    private String mobile;


    @Override
    @JsonIgnore
    public RequestInfo<AuthenticationBlankFourEnCeryQueryResponse> getRequestInfo() {
        RequestInfo<AuthenticationBlankFourEnCeryQueryResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/authentication/blank-encry-query");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(AuthenticationBlankFourEnCeryQueryResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
