package com.github.aiosign.csh;

import cn.hutool.crypto.SmUtil;
import com.alibaba.fastjson.JSON;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CheckAccreditSealResWrapper;
import com.github.aiosign.module.response.CommonResponse;
import com.github.aiosign.utils.SealUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
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
        CheckAccreditSealResWrapper checkAccreditSealResWrapper = new CheckAccreditSealResWrapper(signClient, execute);
        checkAccreditSealResWrapper.wrapper();
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
    }

}
