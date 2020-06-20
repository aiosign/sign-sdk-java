package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.CompanyUserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 企业用户身份信息
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserInfoRequest extends AbstractSignRequest<CompanyUserInfoResponse> {

	/**
	 * 用户id
	 */
	private String userId;

	private byte[] file;

	@Override
	@JsonIgnore
	public RequestInfo<CompanyUserInfoResponse> getRequestInfo() {
		RequestInfo<CompanyUserInfoResponse> requestInfo=new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("user/company/userinfo");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(CompanyUserInfoResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
