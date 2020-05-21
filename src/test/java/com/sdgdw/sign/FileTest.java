package com.sdgdw.sign;

import com.sdgdw.sign.base.FileItem;
import com.sdgdw.sign.module.request.FileUploadRequest;
import com.sdgdw.sign.module.response.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;


/**
 * @author yangyouwang
 * @description 文件Test
 * @since 2020/5/19
 */
@Slf4j
public class FileTest extends AbstractSignTest{

    /**
     * 文件上传
     */
    @Test
    public void upload() {
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType("impression");
        fileUploadRequest.setFileName("测试印章");
        fileUploadRequest.setUserId("00710519812799483904");
        FileItem fileItem=new FileItem(new File("C:\\Users\\Administrator\\Downloads\\wallpaper\\SW-2FBAA3CF876BA6B08A5699E0CEFE9C28.jpg"));
        fileUploadRequest.setFileItem(fileItem);
        FileUploadResponse execute = signClient.execute(fileUploadRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }
}
