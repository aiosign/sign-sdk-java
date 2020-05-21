package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.UserPersonalLockResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 用户个人账户
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalLockRequest extends AbstractSignRequest<UserPersonalLockResponse> {

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 返回请求的必要参数信息
	 *
	 * @return
	 */
	@Override
	@JsonIgnore
	public RequestInfo<UserPersonalLockResponse> getRequestInfo() {
		RequestInfo<UserPersonalLockResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("user/personal/lock");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(UserPersonalLockResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
