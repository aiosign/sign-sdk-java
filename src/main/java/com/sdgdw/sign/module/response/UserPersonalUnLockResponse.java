package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 个人用户锁定或解锁返回结果
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPersonalUnLockResponse  extends AbstractSignResponse {

	private UserPersonalUnLockModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class UserPersonalUnLockModule extends BaseSignObject {
		/**
		 * 用户id
		 */
		private String userId;

		/**
		 * 锁定或解锁的结果 true操作成功 false操作失败
		 */
		private boolean result;
	}
}
