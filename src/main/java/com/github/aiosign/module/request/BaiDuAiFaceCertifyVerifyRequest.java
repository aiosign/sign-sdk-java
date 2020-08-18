package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.BaiDuAiFaceCertifyVerifyResponse;
import com.github.aiosign.module.response.BaiduSessionCodeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 企业打款认证
 *
 * @author modificial
 * @since 2020/4/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaiDuAiFaceCertifyVerifyRequest extends AbstractSignRequest<BaiDuAiFaceCertifyVerifyResponse> {


    /**
     * 认证信息
     */
    private String image;
    /**
     * 身份证号码
     */
    private String idCardNumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 语音验证类型，BASE64,
     */
    private String imageType;


    @Override
    @JsonIgnore
    public RequestInfo<BaiDuAiFaceCertifyVerifyResponse> getRequestInfo() {
        RequestInfo<BaiDuAiFaceCertifyVerifyResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("authentication/baidu/certify-verify");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(BaiDuAiFaceCertifyVerifyResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
