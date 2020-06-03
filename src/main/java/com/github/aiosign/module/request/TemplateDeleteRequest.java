package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.TemplateDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 模板删除参数
 *
 * @author Administrator
 * @version $Id: $Id
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDeleteRequest extends AbstractSignRequest<TemplateDeleteResponse> {

    /**
     * 模板id
     */
    private String templateId;

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public RequestInfo<TemplateDeleteResponse> getRequestInfo() {
        RequestInfo<TemplateDeleteResponse> requestInfo=new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("template/delete");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(TemplateDeleteResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
