package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 企业用户身份信息
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyUserInfoResponse  extends AbstractSignResponse {

	private CompanyUserInfoModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class CompanyUserInfoModule extends BaseSignObject {
		/**
		 * 企业用户信息
		 */
		private CompanyUserInfo companyUserInfo;

		/**
		 * 证书信息
		 */
		private CertInfo certInfo;

		/**
		 * 个人信息
		 */
		@Data
		public static class CompanyUserInfo implements Serializable {

			/**
			 * 用户id
			 */
			private String userId;

			/**
			 * 用户名字
			 */
			private String userName;

			/**
			 * 地区编码
			 */
			private String areaCode;

			/**
			 * 单位类型：01,党政机关、人大、政协;02,企业单位;03,事业单位;04,社会团体;05,民营非企业单位;99,其它
			 */
			private String unitType;

			/**
			 * 统一社会信用代码
			 */
			private String crediCode;

			/**
			 * 法人名称
			 */
			private String legalName;

			/**
			 * 法人证件号
			 */
			private String legalIdNumber;

			/**
			 * 法人证件类型
			 */
			private String legalIdType;

			/**
			 * 法人电话
			 */
			private String legalPhone;

			/**
			 * 法人邮件地址
			 */
			private String legalEmail;

			/**
			 * 经办人名称
			 */
			private String agentName;

			/**
			 * 经办人证件号
			 */
			private String agentIdNumber;

			/**
			 * 经办人证件类型
			 */
			private String agentIdType;

			/**
			 * 经办人电话
			 */
			private String agentPhone;

			/**
			 * 经办人邮箱
			 */
			private String agentEmail;

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
