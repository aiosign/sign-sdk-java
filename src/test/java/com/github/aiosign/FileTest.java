package com.github.aiosign;

import com.github.aiosign.base.FileItem;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.FileUploadRequest;
import com.github.aiosign.module.response.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * @author yangyouwang
 * @description 文件Test
 * @since 2020/5/19
 */
@Slf4j
public class FileTest extends AbstractSignTest {

    /**
     * 上传印章
     */
    @Test
    public void uploadImpression() {
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType("impression");
        fileUploadRequest.setFileName("测试印章");
        fileUploadRequest.setUserId("00716661208384163840");
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Desktop\\1.png"));
        fileUploadRequest.setFileItem(fileItem);
        FileUploadResponse execute = signClient.execute(fileUploadRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 上传合同
     */
    @Test
    public void uploadContract() {
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType("contract");
        fileUploadRequest.setFileName("测试合同");
        fileUploadRequest.setUserId("00716661208384163840");
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Desktop\\房屋合同.pdf"));
        fileUploadRequest.setFileItem(fileItem);
        FileUploadResponse execute = signClient.execute(fileUploadRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 上传模板
     */
    @Test
    public void uploadTemplate() {
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType("template");
        fileUploadRequest.setFileName("测试模板");
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Desktop\\template.pdf"));
        fileUploadRequest.setFileItem(fileItem);
        FileUploadResponse execute = signClient.execute(fileUploadRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 下载文件
     *
     * @throws FileNotFoundException
     */
    @Test
    public void downloadFile() throws FileNotFoundException {
        String baseUri = "/v1/file/download";
        String fileId = "ceafec7cdfaf4f3b8f7af21966a137e9";
        FileOutputStream out = new FileOutputStream("contract.pdf");
        signClient.download(baseUri, fileId, out);
    }

    /**
     * 下载文件存证报告
     *
     * @throws FileNotFoundException
     */
    @Test
    public void downloadReport() throws FileNotFoundException {
        String baseUri = "/v1/file/downloadReport";
        String fileId = "5a743d994e45e729961cb520fef590ce";
        FileOutputStream out = new FileOutputStream("report.pdf");
        signClient.download(baseUri, fileId, out);
    }
}
