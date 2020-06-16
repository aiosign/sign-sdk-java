package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 模本签名参数
 *
 * @author modificial
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateQueryResponse extends AbstractSignResponse {

	private TemplateQueryModule data;
	/**
	 * 模本签名参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class TemplateQueryModule extends BaseSignObject {
		/**
		 * 模板id
		 */
		private String templateId;

		/**
		 * 模板名字
		 */
		private String name;

		/**
		 * 创建时间
		 */
		private LocalDateTime createTime;

		/**
		 * 文件id
		 */
		private String fileId;

		/**
		 * 签名域，签名域信息
		 */
		private String signParams;

		/**
		 * 文字域，文字域信息
		 */
		private String textParams;
		/**
		 * 模板状态 1正常0锁定
		 */
		private String status;
		/**
		 * 模板描述信息
		 */
		private String description;
	}

}
