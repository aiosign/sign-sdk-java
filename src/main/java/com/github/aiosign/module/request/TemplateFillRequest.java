package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.bean.TextParam;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.TemplateFillResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 模板填充参数
 *
 * @author modificial
 * @since 2020/8/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateFillRequest extends AbstractSignRequest<TemplateFillResponse> {
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 合同名字不能为空
     */
    private String name;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 文本域参数
     */
    private List<TextParam> simpleFormFields;

    /**
     * 返回请求的必要参数信息
     *
     * @return a {@link RequestInfo} object.
     */
    @Override
    @JsonIgnore
    public RequestInfo<TemplateFillResponse> getRequestInfo() {
        RequestInfo<TemplateFillResponse> requestInfo = new RequestInfo<>();
        requestInfo.setApiUri("template/fill");
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(TemplateFillResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
