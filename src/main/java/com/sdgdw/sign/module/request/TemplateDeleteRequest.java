package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.TemplateDeleteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 模板删除参数
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
