package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 合同id集合
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsyContractIdentityRequest extends AbstractSignRequest<ContractAddResponse> {

	/**
	 * 合同id
	 */
	private String contractId;

	@Override
	@JsonIgnore
	public RequestInfo<ContractAddResponse> getRequestInfo() {
		RequestInfo<ContractAddResponse> requestInfo = new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("/v1/contract/render");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(ContractAddResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
