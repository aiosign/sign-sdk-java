package com.github.aiosign;

import com.github.aiosign.base.FileItem;
import com.github.aiosign.module.request.BarCodeRequest;
import com.github.aiosign.module.request.FileCheckRequest;
import com.github.aiosign.module.request.FileIdentityRequest;
import com.github.aiosign.module.response.SignCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * @author yangyouwang
 * @description 验签Test
 * @since 2020/5/13
 */
@Slf4j
public class SignCheckTest extends AbstractSignTest {


    /**
     * 根据签章文件id验签
     */
    @Test
    public void common() {
        FileIdentityRequest fileIdentityRequest = new FileIdentityRequest();
        // 文件id
        fileIdentityRequest.setFileId("00004223f9648c805374675db1778590");
        SignCheckResponse execute = signClient.execute(fileIdentityRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 根据文件验签
     */
    @Test
    public void file() {
        FileCheckRequest fileCheckRequest = new FileCheckRequest();
        // 文件id
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Downloads\\wallpaper\\SW-2FBAA3CF876BA6B08A5699E0CEFE9C28.jpg"));
        fileCheckRequest.setFileItem(fileItem);
        SignCheckResponse execute = signClient.execute(fileCheckRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 条形码验签
     */
    @Test
    public void barCodeCheck() {
        BarCodeRequest barCodeRequest = new BarCodeRequest();
        // 条形码请求参数
        barCodeRequest.setBarCode("");
        SignCheckResponse execute = signClient.execute(barCodeRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
