package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加扫描信息结果相应
 * @author WeiShuai
 * @Date 2020/9/18 0018 下午 02:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ScanContractAddResponse extends AbstractSignResponse {
    private String prepareId;
    private String qr;
}
