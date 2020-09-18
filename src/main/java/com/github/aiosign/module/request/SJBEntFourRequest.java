package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SJBEntFourResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 合同id集合
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SJBEntFourRequest extends AbstractSignRequest<SJBEntFourResponse> {

    /**
     * 姓名
     */
    private String name;
    /**
     * 企业名称
     */
    private String entname;
    /**
     * 企业标识
     */
    private String entmark;
    /**
     * 个人身份证号码
     */
    private String idcard;


    @Override
    @JsonIgnore
    public RequestInfo<SJBEntFourResponse> getRequestInfo() {
        RequestInfo<SJBEntFourResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/authentication/entFour");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SJBEntFourResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
