package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.bean.CustomSignFields;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.BatchTemplateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 批量签章参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchTemplateRequest extends AbstractSignRequest<BatchTemplateResponse> {

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 批量签章参数
     */
    private List<CustomSignFields> batchTemplates;

	@Override
	@JsonIgnore
	public RequestInfo<BatchTemplateResponse> getRequestInfo() {
		RequestInfo<BatchTemplateResponse> requestInfo = new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("/v1/sign/template/batch");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(BatchTemplateResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
