package com.github.aiosign.csh;

import com.alibaba.fastjson.JSON;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
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
 * @date 2023/5/30
 */
@Slf4j
public class SignCheckCshTest extends AbstractSignTest {

    /**
     * 根据文件验签
     */
    @Test
    public void fileV2() {
        FileItem fileItem = new FileItem(new File("D:\\contract\\我的合同.pdf"));

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v2/sign/check/file");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.MULTIPART);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file", fileItem);
        request.setFileParams(map);
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
    }


    /**
     * 根据签章文件id验签
     */
    @Test
    public void common() {
        HashMap<String, Object> fileIdentityRequestBody = new HashMap<>();
        // 文件id
        fileIdentityRequestBody.put("file_id","00004223f9648c805374675db1778590");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/sign/check/common"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(fileIdentityRequestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
    }
}
