package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.base.ResponseCode;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.UserPersonalRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 个人用户注册请求对象
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalRegisterRequest extends AbstractSignRequest<UserPersonalRegisterResponse> {

	/**
	 * 用户名（必填）
	 */
	private String userName;

	/**
	 * 地区编码（必填）
	 */
	private String areaCode;

	/**
	 * 手机号（必填）
	 */
	private String phone;

	/**
	 * 证件类型（必填）
	 */
	private String idType;

	/**
	 * 证件号（必填）
	 */
	private String idNumber;

	/**
	 * 邮箱地址（非必填）
	 */
	private String mail;

	/**
	 * 描述信息
	 */
	private String description;

	/**
	 * 返回请求的必要参数信息
	 *
	 * @return
	 */
	@Override
	@JsonIgnore
	public RequestInfo<UserPersonalRegisterResponse> getRequestInfo() {
		RequestInfo<UserPersonalRegisterResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("user/personal/register");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(UserPersonalRegisterResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
