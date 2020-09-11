package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.UserCompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 企业用户注册
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyRegisterRequest extends AbstractSignRequest<UserCompanyResponse> {

	/**
	 * 企业名称
	 */
	private String userName;

	/**
	 * 地区编码
	 */
	private String areaCode;

	/**
	 * 单位类型：1,企业;2,事业单位;3,社会团体;4,其他
	 */
	private String unitType;

	/**
	 * 统一社会信用代码
	 */
	private String crediCode;

	/**
	 * 法人名称
	 */
	private String legalName;

	/**
	 * 法人证件号
	 */
	private String legalIdNumber;

	/**
	 * 法人证件类型
	 */
	private String legalIdType;

	/**
	 * 法人电话
	 */
	private String legalPhone;

	/**
	 * 法人的邮件地址
	 */
	private String legalEmail;

	/**
	 * 经办人名称
	 */
	private String agentName;

	/**
	 * 经办人证件号
	 */
	private String agentIdNumber;

	/**
	 * 经办人证件类型
	 */
	private String agentIdType;

	/**
	 * 经办人电话
	 */
	private String agentPhone;

	/**
	 * 经办人邮箱
	 */
	private String agentEmail;

	/**
	 * 描述信息
	 */
	private String description;

	@Override
	@JsonIgnore
	public RequestInfo<UserCompanyResponse> getRequestInfo() {
		RequestInfo<UserCompanyResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("/v1/user/company/register");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(UserCompanyResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
