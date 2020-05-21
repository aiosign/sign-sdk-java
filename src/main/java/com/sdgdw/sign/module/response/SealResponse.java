package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 印章操作结果
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SealResponse extends AbstractSignResponse {

	private SealModule data;
	/**
	 * 印章参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class SealModule extends BaseSignObject {
		/**
		 * 印章id
		 */
		private String sealId;
	}
}
