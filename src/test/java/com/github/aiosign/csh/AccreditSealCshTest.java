package com.github.aiosign.csh;

import cn.hutool.crypto.SmUtil;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 城商行定制
 * <p>
 * 均使用原始调用Api，除上传以及下载文件，实体类方式后期不再维护
 * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
 * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
 *
 * @author Zhu Dunfeng
 * @date 2023/4/23
 */
@Slf4j
public class AccreditSealCshTest extends AbstractSignTest {

    /**
     * 核验授权信息
     */
    @Test
    public void checkAccreditSealInfo() {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("check_seal_types", "01,02");

        HashMap<String, Object> companyInfo = new HashMap<>();
        companyInfo.put("credit_code", "913708782023032237");
        companyInfo.put("user_name", "测试领取企业单位2023-37");
        requestBody.put("company_info", companyInfo);

        HashMap<String, Object> personalInfo = new HashMap<>();
        personalInfo.put("user_name", "李万洋");
        personalInfo.put("phone", "17854319709");
        personalInfo.put("id_number", SmUtil.sm3("371523199705170016"));
        personalInfo.put("mail", "");
        personalInfo.put("id_type", "111");
        personalInfo.put("description", "测试");
        requestBody.put("personal_info", personalInfo);

        CommonRequest request = new CommonRequest();
        request.setApiUri("/accredit/cshlm/v1/accredit-info");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 合并8.3.1获取用户签章与8.7.1核验用户信息接口
     */
    @Test
    public void checkAccreditSealInfoAndGetSealInfo() {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("check_seal_types", "01,02");

        HashMap<String, Object> companyInfo = new HashMap<>();
        companyInfo.put("credit_code", "913708782023032237");
        companyInfo.put("user_name", "测试领取企业单位2023-37");
        requestBody.put("company_info", companyInfo);

        HashMap<String, Object> personalInfo = new HashMap<>();
        personalInfo.put("user_name", "李万洋");
        personalInfo.put("phone", "17854319709");
        personalInfo.put("id_number", SmUtil.sm3("371523199705170016"));
        personalInfo.put("mail", "");
        personalInfo.put("id_type", "111");
        personalInfo.put("description", "测试");
        requestBody.put("personal_info", personalInfo);

        CommonRequest request = new CommonRequest();
        request.setApiUri("/accredit/cshlm/v1/accredit-info");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
        // 解析响应数据
        HashMap<String, String> response = (HashMap<String, String>) execute.getData();
        // 核验授权信息接口获取到企业id，然后传入获取用户签章接口继续查看当前企业印章信息
        String companyUserId = response.get("company_id");

        HashMap<String, String> sealRequestBody = new HashMap<>(2);
        sealRequestBody.put("user_id",companyUserId);

        CommonRequest sealRequest = new CommonRequest();
        sealRequest.setApiUri("/v1/seal/getSealInfos"); // 请求Api地址
        sealRequest.setNeedToken(true);// 是否需要token
        sealRequest.setContentType(ContentType.JSON);// 请求头类型
        sealRequest.setMethod(HttpMethod.POST);// 请求方法
        sealRequest.setRequestBody(sealRequestBody);// 请求体

        CommonResponse sealExecute = signClient.execute(request);
        log.info("响应状态：{}", sealExecute.getResultCode());
        log.info("响应信息：{}", sealExecute.getResultMessage());
        log.info("响应数据：{}", sealExecute.getData());

        List<HashMap<String, String>> sealInfoModuleList = (List<HashMap<String, String>>) execute.getData();
        sealInfoModuleList.forEach(map -> {
            String sealId = map.get("seal_id");
            String size = map.get("size");
            System.out.printf("sealInfo: sealId:%s -> size:%s", sealId, size);
            List<String> collect = Arrays.asList(size.trim().split("\\*"));
            String width = collect.get(0);
            String high = collect.get(0);
            // 计算后的尺寸
            System.out.printf("width:%s,high:%s\n", width, high);
        });
    }

}
