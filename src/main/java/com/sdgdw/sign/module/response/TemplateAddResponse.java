package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板添加返回参数
 *
 * @author modificial
 * @since 2020/4/5
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateAddResponse extends AbstractSignResponse {

	private TemplateAddModule data;
	/**
	 * 模本签名参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class TemplateAddModule extends BaseSignObject {
		/**
		 * 模板id
		 */
		private String templateId;
	}
}
