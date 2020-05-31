package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.ContractAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 合同添加参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractAddRequest extends AbstractSignRequest<ContractAddResponse> {

	/**
	 * 合同名字
	 */
	private String name;

	/**
	 * 文件id
	 */
	private String fileId;

    /**
     * 用户id不能为空
     */
    private String userId;

    /**
     * 描述信息
     */
    private String description;

    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public RequestInfo<ContractAddResponse> getRequestInfo() {
        RequestInfo<ContractAddResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("contract/add");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractAddResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
