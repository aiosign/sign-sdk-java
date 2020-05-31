package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合同删除返回参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractDeleteResponse extends AbstractSignResponse {

	private ContractDeleteModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class ContractDeleteModule extends BaseSignObject {
		/**
		 * 合同id
		 */
		private String contractId;

		/**
		 * 删除的结果 true操作成功 false操作失败
		 */
		private boolean result;
	}

}
