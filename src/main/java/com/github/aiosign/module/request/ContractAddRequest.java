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
