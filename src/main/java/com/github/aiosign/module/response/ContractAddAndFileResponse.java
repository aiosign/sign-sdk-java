package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;

/**
 * @author 侯存路
 * @description
 * @since 2020/6/16 9:28
 */
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
