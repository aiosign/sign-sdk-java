package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板锁定或解锁返回结果
 *
 * @author Administrator
 * @version $Id: $Id
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateLockResponse extends AbstractSignResponse {

	private TemplateLockModule data;
	/**
	 * 模本签名参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class TemplateLockModule extends BaseSignObject {
		/**
		 * 模板id
		 */
		private String templateId;

		/**
		 * 锁定或解锁的结果 true操作成功 false操作失败
		 */
		private boolean result;
	}
}
