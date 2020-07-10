package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @since 2020/6/16 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContractAddAndFileResponse extends AbstractSignResponse {


    /**
     * 合同id
     */
    private String contractId;

    /**
     * 文件id
     */
    private String fileId;


}
