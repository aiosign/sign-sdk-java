package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合同添加返回值
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractAddResponse extends AbstractSignResponse {

	private ContractAddModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class ContractAddModule extends BaseSignObject {
		/**
		 * 合同id
		 */
		private String contractId;
	}
}
