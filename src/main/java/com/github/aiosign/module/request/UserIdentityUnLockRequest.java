package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserCompanyUnLookResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 用户唯一标识请求参数
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentityUnLockRequest extends AbstractSignRequest<UserCompanyUnLookResponse> {

	/**
	 * 用户id
	 */
	private String userId;

	@Override
	@JsonIgnore
	public RequestInfo<UserCompanyUnLookResponse> getRequestInfo() {
		RequestInfo<UserCompanyUnLookResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("user/company/unlock");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(UserCompanyUnLookResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
