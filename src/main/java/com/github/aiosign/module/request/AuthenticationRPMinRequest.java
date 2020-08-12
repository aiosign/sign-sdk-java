package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.bean.Picture;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.AuthenticationRPMinResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 10:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRPMinRequest extends AbstractSignRequest<AuthenticationRPMinResponse> {


    /**
     * 姓名
     */
    private String name;
    /**
     * 银行卡号
     */
    private String idCardNumber;
    /**
     * 实人照片url
     */
    private Picture faceImage;
    /**
     * 身份证背面信息
     */
    private Picture idCardBackImage;
    /**
     * 身份证正面信息
     */
    private Picture idCardFrontImage;





    @Override
    @JsonIgnore
    public RequestInfo<AuthenticationRPMinResponse> getRequestInfo() {
        RequestInfo<AuthenticationRPMinResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/real-people");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(AuthenticationRPMinResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }


}
