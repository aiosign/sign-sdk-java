package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业用户注册，解锁，注销返回数据
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCompanyUnLookResponse  extends AbstractSignResponse {

	private UserCompanyUnLookModule data;
	/**
	 * 用户解锁返回数据
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class UserCompanyUnLookModule extends BaseSignObject {
		/**
		 * 企业用户id
		 */
		private String userId;

		private Boolean result;
	}

}
