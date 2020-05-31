package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 印章查询返回参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SealQueryResponse  extends AbstractSignResponse {


	private BatchTemplateModule data;

	/**
	 * 模本签名参数
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class BatchTemplateModule extends BaseSignObject {
		/**
		 * 用户id
		 */
		private String userId;

		/**
		 * 印章名字
		 */
		private String sealName;

		/**
		 * 印章类型
		 */
		private String sealType;

		/**
		 * 印章文件id
		 */
		private String fileId;

		/**
		 * 印章规格
		 */
		private String size;

		/**
		 * 印章状态
		 */
		private String status;
        /**
         * 印章描述信息
         */
		private String description;
	}


}
