package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 批量操作返回参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SealBatchResponse extends AbstractSignResponse {

	private SealBatchModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class SealBatchModule extends BaseSignObject {
		/**
		 * 印章id
		 */
		private String sealId;

		/**
		 * 删除的结果 true操作成功 false操作失败
		 */
		private boolean result;
	}
}
