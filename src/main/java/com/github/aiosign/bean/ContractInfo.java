package com.github.aiosign.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 合同信息
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/8
 */
@Data
public class ContractInfo implements Serializable {

	/**
	 * 第几页合同信息
	 */
	private int number;

	/**
	 * 图片文件id
	 */
	private String imageFileId;

}
