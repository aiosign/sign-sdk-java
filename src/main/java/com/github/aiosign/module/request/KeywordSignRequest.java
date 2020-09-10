package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SignResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 关键字签章参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeywordSignRequest extends AbstractSignRequest<SignResponse> {

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 印章id
     */
    private String sealId;

    /**
     * 关键字不能为空
     */
    private String keyword;

    /**
     * 印章高度，精确1位小数
     */
    private Double height;

    /**
     * 印章宽度，精确1位小数
     */
    private Double width;

    /**
     * true：合同内所有匹配位置全部签署；false：只签署第一个匹配；默认false
     */
    private Boolean signAll;

    /**
     * 用户id
     */
    private String userId;


    @Override
    @JsonIgnore
    public RequestInfo<SignResponse> getRequestInfo() {
        RequestInfo<SignResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("sign/keywordSign");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
