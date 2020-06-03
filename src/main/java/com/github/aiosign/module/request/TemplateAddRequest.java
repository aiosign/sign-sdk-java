package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.TemplateAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 模板添加请求参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateAddRequest extends AbstractSignRequest<TemplateAddResponse> {

    /**
     * 模板文件id
     */
    private String fileId;
    /**
     * 模板名字
     */
    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public RequestInfo<TemplateAddResponse> getRequestInfo() {
        RequestInfo<TemplateAddResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("template/add");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(TemplateAddResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}