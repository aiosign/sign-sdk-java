package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import com.github.aiosign.bean.ContractInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同添加返回值
 *
 * @author modificial
 * @since 2020/4/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractQueryResponse extends AbstractSignResponse {

	private ContractQueryModule data;

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class ContractQueryModule extends BaseSignObject {
		/**
		 * 合同id
		 */
		private String contractId;

		/**
		 * 合同名字
		 */
		private String name;

		/**
		 * 合同大小
		 */
		private Double size;

		/**
		 * 合同创建时间
		 */
		private LocalDateTime createTime;

		/**
		 * 文件id
		 */
		private String fileId;

		/**
		 * 合同状态：0，删除；1，无效；2，生效；3，废除
		 */
		private String status;

		/**
		 * 合同页数
		 */
		private Integer pageCount;

		/**
		 * 合同详细数据
		 */
		private List<ContractInfo> contractInfos;

		/**
		 * 是否渲染完成 0渲染失败1渲染成功2渲染中
		 */
		private String renderComplete;
	}
}
