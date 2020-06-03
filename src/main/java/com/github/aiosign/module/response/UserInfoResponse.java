package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户详情信息返回
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoResponse extends AbstractSignResponse {

	private UserInfoModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class UserInfoModule extends BaseSignObject {
		/**
		 * 个人用户信息
		 */
		private PersonalInfo personalInfo;

		/**
		 * 证书信息
		 */
		private CertInfo certInfo;

		/**
		 * 个人信息
		 */
		@Data
		public static class PersonalInfo implements Serializable {

			/**
			 * 用户id
			 */
			private String userId;

			/**
			 * 地区编码
			 */
			private String areaCode;

			/**
			 * 用户名
			 */
			private String userName;

			/**
			 * 用户手机号
			 */
			private String phone;

			/**
			 * 用户邮箱
			 */
			private String mail;

			/**
			 * 用户身份证件类型
			 */
			private String idType;

			/**
			 * 用户证件类型
			 */
			private String idNumber;

			/**
			 * 用户状态
			 */
			private String status;

		}

		/**
		 * 个人证书信息
		 *
		 * @author Administrator
		 */
		@Data
		public static class CertInfo implements Serializable {

			/**
			 * 证书id
			 */
			private String certId;

			/**
			 * 证书序列号
			 */
			private String sn;

			/**
			 * 证书名字
			 */
			private String certName;

			/**
			 * 证件号码
			 */
			private String idNumber;

			/**
			 * 颁发者
			 */
			private String issuer;

			/**
			 * 版本号
			 */
			private Integer version;

			/**
			 * 证书生效时间
			 */
			private String startDate;

			/**
			 * 证书失效时间
			 */
			private String endDate;

			/**
			 * 证书使用者名称
			 */
			private String awardTo;

			/**
			 * 证书状态
			 */
			private String status;

		}
	}



}
