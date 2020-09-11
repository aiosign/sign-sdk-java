package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.TemplateQueryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 模板参数
 *
 * @author modificial
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateQueryRequest extends AbstractSignRequest<TemplateQueryResponse> {

	/**
	 * 模板id
	 */
	private String templateId;

	@Override
	@JsonIgnore
	public RequestInfo<TemplateQueryResponse> getRequestInfo() {
		RequestInfo<TemplateQueryResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("/v1/template/query");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(TemplateQueryResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
