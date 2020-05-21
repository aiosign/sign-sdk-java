package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 批量签章参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BatchTemplateResponse  extends AbstractSignResponse {

	private BatchTemplateModule data;
	/**
	 * 模本签名参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class BatchTemplateModule extends BaseSignObject {
		/**
		 * 批次号
		 */
		private String batchId;

		/**
		 * 签章基本结果信息
		 */
		private List<SignInfo> signInfos;

		/**
		 * 签章结果
		 */
		@Data
		public static class SignInfo implements Serializable {

			/**
			 * 签章id
			 */
			private String signId;

			/**
			 * 合同文件id
			 */
			private String fileId;

			/**
			 * 签章状态
			 */
			private Boolean signState;
			/**
			 * 自定义id
			 */
			public String customId;
		}
	}


}
