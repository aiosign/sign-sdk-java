package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.ContractQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 合同id集合
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractIdentityRequest extends AbstractSignRequest<ContractQueryResponse> {

	/**
	 * 合同id
	 */
	private String contractId;


	@Override
	@JsonIgnore
	public RequestInfo<ContractQueryResponse> getRequestInfo() {
		RequestInfo<ContractQueryResponse> requestInfo = new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("contract/query");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(ContractQueryResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
