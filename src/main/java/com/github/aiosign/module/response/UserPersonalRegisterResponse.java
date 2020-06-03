package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 个人用户注册返回信息
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPersonalRegisterResponse extends AbstractSignResponse {
	/**
	 * 用户注册返回数据
	 */
	private UserPersonalRegisterModule data;

	/**
	 * 用户注册返回数据
	 */
	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class UserPersonalRegisterModule extends BaseSignObject {
		/**
		 * 用户id
		 */
		private String userId;
	}

}
