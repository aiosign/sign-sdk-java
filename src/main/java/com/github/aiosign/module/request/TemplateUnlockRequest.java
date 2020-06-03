package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.TemplateUnlockResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 模板解锁参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateUnlockRequest extends AbstractSignRequest<TemplateUnlockResponse> {

    /**
     * 模板id,逗号隔开
     */
    private String templateId;


    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public RequestInfo<TemplateUnlockResponse> getRequestInfo() {
        RequestInfo<TemplateUnlockResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("template/unlock");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(TemplateUnlockResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }

}
