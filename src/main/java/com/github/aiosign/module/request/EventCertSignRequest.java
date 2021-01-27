package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SignResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 普通签章请求参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCertSignRequest extends AbstractSignRequest<SignResponse> {

	/**
	 * 自定义签署ID
	 */
	private String signId;

	/**
	 * 合同id
	 */
	private String contractId;

	/**
	 * 签章备注
	 */
	private String remark;

	/**
	 * 签章信息集合
	 */
	private List<SignParams> fields;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 签章参数
	 */
	@Data
	public static class SignParams implements Serializable {

		/**
		 * 印章id
		 */
		private String sealId;

		/**
		 * 第几页
		 */
		private Integer pageNumber;

		/**
		 * 水平横坐标
		 */
		private Double horizontal;

		/**
		 * 垂直纵坐标
		 */
		private Double vertical;

		/**
		 * 印章宽度，精确1位小数
		 */
		private Double width;

		/**
		 * 印章高度，精确1位小数
		 */
		private Double height;

	}

	@Override
	@JsonIgnore
	public RequestInfo<SignResponse> getRequestInfo() {
		RequestInfo<SignResponse> requestInfo = new RequestInfo<>();
		requestInfo.setContentType(ContentType.JSON);
		requestInfo.setApiUri("/v1/event_cert_sign/common");
		requestInfo.setMethod(HttpMethod.POST);
		requestInfo.setNeedToken(true);
		requestInfo.setResponseType(SignResponse.class);
		requestInfo.setRequestBody(this);
		return requestInfo;
	}
}
