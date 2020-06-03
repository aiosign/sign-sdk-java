package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
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
public class TemplateUnlockResponse  extends AbstractSignResponse {

	private TemplateUnlockModule data;
	/**
	 * 模本签名参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class TemplateUnlockModule extends BaseSignObject {
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
