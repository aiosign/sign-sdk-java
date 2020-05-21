package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.TemplateUnlockResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 模板解锁参数
 *
 * @author modificial
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


	@Override
	@JsonIgnore
	public RequestInfo<TemplateUnlockResponse> getRequestInfo() {
		RequestInfo<TemplateUnlockResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("template/unlock");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(TemplateUnlockResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}

}
