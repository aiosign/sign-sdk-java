package com.github.aiosign.csh;

import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 城商行定制
 * <p>
 * 均使用原始调用Api，除上传以及下载文件，实体类方式后期不再维护
 * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
 * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
 *
 * @author Zhu Dunfeng
 * @date 2023/5/29
 */
@Slf4j
public class FileCshTest extends AbstractSignTest {

    /**
     * 上传合同
     */
    @Test
    public void uploadContract() {
        String fileName = "测试合同";
        String fileType = "contract";
        String userId = "10144942136450173440";
        // FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Desktop\\房屋合同.pdf"));
        FileItem fileItem = new FileItem(new File("E:\\worktwo\\csh\\三方签署测试pdf\\bd6ad2b269b6bc4babdef71d95ae6037.pdf"));

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/file/upload");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.MULTIPART);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file", fileItem);
        request.setFileParams(map);
        Map<String, String> params = request.getParams();
        params.put("file_name", fileName);
        params.put("file_type", fileType);
        params.put("user_id", userId);
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 上传模板
     */
    @Test
    public void uploadTemplate() {
        String fileName = "测试模板";
        String fileType = "template";
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Desktop\\template.pdf"));

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/file/upload");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.MULTIPART);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file", fileItem);
        request.setFileParams(map);
        Map<String, String> params = request.getParams();
        params.put("file_name", fileName);
        params.put("file_type", fileType);
        CommonResponse execute = signClient.execute(request);
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
