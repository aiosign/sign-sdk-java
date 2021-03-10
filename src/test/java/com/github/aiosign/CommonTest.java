package com.github.aiosign;

import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;

/**
 * 提供原始调用签章Api TEST
 *
 * @author modificial
 * @since 2020/4/3
 */
@Slf4j
public class CommonTest extends AbstractSignTest {

    /**
     * 原始调用Api，除上传以及下载文件
     * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
     * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
     */
    @Test
    public void callApiReturnObject() {
        SignClient signClient = new DefaultSignClient("http://172.30.162.251:8888/api","745678846409986048","CwHyZlnqmsCnOaCSsP");
        //请求参数 具体请看调用Api请求参数
        HashMap<String, String> requestBody = new HashMap<>(2);
        requestBody.put("file_id", "efb9bbb33773172662a8ef4c94338212");
//        requestBody.put("seal_types", "01");
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/file/syncFile"); //请求Api地址
        request.setNeedToken(true);//是否需要token
        request.setContentType(ContentType.JSON);//请求头类型
        request.setMethod(HttpMethod.POST);//请求方法
        request.setRequestBody(requestBody);//请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
        //解析响应数据
        HashMap<String, String> response = (HashMap<String, String>) execute.getData();
        if (response != null) {
            log.info("提取-印章名称：{}", response.get("seal_name") == null ? "属性未提取到" : response.get("seal_name"));
        }
    }

    /**
     * 原始调用Api，除上传以及下载文件
     * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
     * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
     */
    @Test
    public void callApiReturnList() {
        //请求参数
        HashMap<String, String> requestBody = new HashMap<>(2);
        requestBody.put("user_id", "00740939959804514304");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/seal/getSealInfos"); //请求Api地址
        request.setNeedToken(true);//是否需要token
        request.setContentType(ContentType.JSON);//请求头类型
        request.setMethod(HttpMethod.POST);//请求方法
        request.setRequestBody(requestBody);//请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
        //解析响应数据
        List<HashMap<String, String>> response = (List<HashMap<String, String>>) execute.getData();
        if (response.size() > 0) {
            log.info("提取-第一个印章的名称：{}", response.get(0).get("seal_name") == null ? "属性未提取到" : response.get(0).get("seal_name"));
        }
    }
}
