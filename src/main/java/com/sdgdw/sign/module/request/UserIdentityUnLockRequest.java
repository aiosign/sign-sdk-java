package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.UserCompanyUnLookResponse;
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
