package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangyouwang
 * @description 文件上传响应
 * @since 2020/5/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FileUploadResponse extends AbstractSignResponse {


    private FileUploadModule data;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class FileUploadModule extends BaseSignObject {
        /**
         * 文件id
         */
        private String fileId;

        /**
         * 文件名字
         */
        private String fileName;

        /**
         * 文件类型
         */
        private String fileType;
    }
}
