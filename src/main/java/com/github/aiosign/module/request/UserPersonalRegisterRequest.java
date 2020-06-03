package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserPersonalRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 个人用户注册请求对象
 *
 * @author modificial
 * @version $Id: $Id
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
	 * {@inheritDoc}
	 * <p>
	 * 返回请求的必要参数信息
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
