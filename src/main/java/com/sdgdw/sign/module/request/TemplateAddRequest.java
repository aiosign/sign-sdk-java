package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.TemplateAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 模板添加请求参数
 *
 * @author modificial
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

	@Override
	@JsonIgnore
	public RequestInfo<TemplateAddResponse> getRequestInfo() {
		RequestInfo<TemplateAddResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("template/add");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(TemplateAddResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
