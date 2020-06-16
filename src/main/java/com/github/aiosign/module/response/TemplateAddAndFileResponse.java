package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;

/**
 * @author 侯存路
 * @description
 * @since 2020/6/16 9:28
 */
@Data
public class TemplateAddAndFileResponse extends AbstractSignResponse {


    /**
     * 模板id
     */
    private String templateId;

    /**
     * 文件id
     */
    private String fileId;


}
