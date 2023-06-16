package com.github.aiosign.csh;

import com.alibaba.fastjson.JSON;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.request.EventCertKeywordSignRequest;
import com.github.aiosign.module.response.CommonResponse;
import com.github.aiosign.utils.SealUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 城商行定制
 * <p>
 * 均使用原始调用Api，除上传以及下载文件，实体类方式后期不再维护
 * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
 * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
 *
 * @author Zhu Dunfeng
 * @date 2023/5/26
 */
@Slf4j
public class SignCshTest extends AbstractSignTest {
    /**
     * 普通签章
     */
    @Test
    public void eventCertSign1() {
        HashMap<String, Object> requestBody = new HashMap<>();
        // 自定义签署ID
        requestBody.put("sign_id", "111");
        // 是否完结合同
        requestBody.put("is_contract_finish", 0);
        // 是否渲染页面
        requestBody.put("is_render", 0);
        // 合同id
        requestBody.put("contract_id", "1fd162f81b9e9e2364b287549670595c");
        // 签章备注
        requestBody.put("remark", "测试");

        ArrayList<Map<String, Object>> signParamList = new ArrayList<>();
        HashMap<String, Object> signParams = new HashMap<>();
        signParams.put("seal_id", "a9e48474650448709a3b577ce4f72234");
        // 页码
        signParams.put("page_number", 1);
        // 水平横坐标
        signParams.put("horizontal", 100D);
        // 垂直纵坐标
        signParams.put("vertical", 100D);
        // 印章宽度(使用工具类转为像素)
        signParams.put("width", SealUtils.transitionSizeToPixel(40D));
        // 印章高度(使用工具类转为像素)
        signParams.put("height", SealUtils.transitionSizeToPixel(40D));
        // 印章旋转角度
        signParams.put("rotate", 0.0D);
        // 签章模式
        signParams.put("layout", 1);

        signParamList.add(signParams);
        // 签章信息集合
        requestBody.put("fields", signParamList);
        // 用户id
        requestBody.put("user_id", "10144942136450173440");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/event_cert_sign/common");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
        HashMap<String, Object> signModule = (HashMap<String, Object>) execute.getData();
        boolean signState = (boolean) signModule.get("sign_state");
        if(!signState){
            //失败原因
            String reason = String.valueOf(signModule.get("reason"));
            log.info("失败原因:{}",reason);
        }
    }


    /**
     * 会议综合签章
     */
    @Test
    public void meetComprehensiveSign() {
        HashMap<String, Object> requestBody = new HashMap<>();
        // 合同ID
        requestBody.put("contract_id", "1fd162f81b9e9e2364b287549670595c");
        // 是否完结
        requestBody.put("is_contract_finish", 0);

        // 授权签章信息
        ArrayList<Map<String, Object>> authSignDetails = new ArrayList<>();
        // 授权人ID
        HashMap<String, Object> authSignDetail = new HashMap<>();
        authSignDetail.put("auth_user_id", "10822386355627249664");
        authSignDetail.put("auth_seal_id","3d2d7ee8ed72b1b3bdea8c09d80bf8cb");
        authSignDetail.put("sign_user_id","10822386355627249664");
        authSignDetail.put("layout",1);
        authSignDetail.put("page_number",1);
        authSignDetail.put("width", SealUtils.transitionSizeToPixel(50.0));
        authSignDetail.put("heignt", SealUtils.transitionSizeToPixel(50.0));
        authSignDetail.put("horizontal",100);
        authSignDetail.put("vertical",200);
        authSignDetail.put("rotate",0.0D);
        authSignDetails.add(authSignDetail);
        requestBody.put("auth_sign_details",authSignDetails);


        // 签署信息
        List<Map<String, Object>> signDetails = new ArrayList<>();
        Map<String, Object> signDetail = new HashMap<>();
        // 签署页码
        signDetail.put("page_number", 1);
        // 印章ID
        signDetail.put("seal_id", "3d2d7ee8ed72b1b3bdea8c09d80bf8cb");
        signDetail.put("user_id", "00821065058230095872");
        signDetail.put("horizontal", 10);
        signDetail.put("vertical", 10);
        signDetail.put("height", SealUtils.transitionSizeToPixel(50.0));
        signDetail.put("width", SealUtils.transitionSizeToPixel(50.0));
        signDetails.add(signDetail);
        requestBody.put("sign_details", signDetails);

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/sign/meeting/comprehensive");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
        HashMap<String, Object> signModule = (HashMap<String, Object>) execute.getData();
        boolean signState = (boolean) signModule.get("sign_state");
        if(!signState){
            //失败原因
            String reason = String.valueOf(signModule.get("reason"));
            log.info("失败原因:{}",reason);
        }
    }

    /**
     * 模板签章接口-单
     */
    @Test
    public void singleSignTemplate() {
        String templateId = "";
        String userId = "";
        String sealId = "";
        HashMap<String, Object> signReq = new HashMap<>();
        // 1.写入需要使用的模板id
        signReq.put("template_id", templateId);
        // 创建隐藏域信息
        HashMap<String, Object> signField = new HashMap<>();

        // 2.创建签名域信息
        List<HashMap<String, Object>> signParams = createSignParams(userId, sealId);
        // 写入签名域信息
        signField.put("sign_params", signParams);
        // 3.创建文字域信息
        List<HashMap<String, Object>> textParams = createTextParams();
        // 写入文字域信息
        signField.put("text_params", textParams);

        // 放入签名域与文字域信息
        signReq.put("sign_field", signField);

        // 执行签章
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/event_cert_sign/template/single");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(signReq);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
        HashMap<String, Object> signModule = (HashMap<String, Object>) execute.getData();
        boolean signState = (boolean) signModule.get("sign_state");
        if(!signState){
            //失败原因
            String reason = String.valueOf(signModule.get("reason"));
            log.info("失败原因:{}",reason);
        }
    }

    /**
     * 模板签章接口-批量
     */
    @Test
    public void batchSignTemplate() {
        String templateId = "";
        String userId = "";
        String sealId = "";
        // 自定义sign_id
        String signId = "";
        // 自定义参数
        String customId = "";

        HashMap<String, Object> requestBody = new HashMap<>();
        // 1.写入需要使用的模板id
        requestBody.put("template_id", templateId);

        List<HashMap<String, Object>> batchTemplates = new ArrayList<>();
        // 创建隐藏域信息
        HashMap<String, Object> custSignField = new HashMap<>();

        custSignField.put("sign_id", signId);

        custSignField.put("custom_id", customId);

        // 2.创建签名域信息
        List<HashMap<String, Object>> signParams = createSignParams(userId, sealId);
        // 写入签名域信息
        custSignField.put("sign_params", signParams);
        // 3.创建文字域信息
        List<HashMap<String, Object>> textParams = createTextParams();
        // 写入文字域信息
        custSignField.put("text_params", textParams);

        // 放入签名域与文字域信息
        batchTemplates.add(custSignField);
        requestBody.put("batch_templates", batchTemplates);
        // 执行签章
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/event_cert_sign/template/batch");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
        HashMap<String, Object> signModule = (HashMap<String, Object>) execute.getData();
        List<HashMap<String, Object>> signInfos = (List<HashMap<String, Object>>) signModule.get("sign_infos");
        for (HashMap<String, Object> signInfo : signInfos) {
            boolean signState = (boolean) signInfo.get("sign_state");
            if(!signState){
                //失败原因
                String reason = String.valueOf(signInfo.get("reason"));
                log.info("失败原因:{}",reason);
            }
        }
    }


    @Test
    public void keywordSign() {
        // 自定义sign_id
        String signId = "";
        String contractId = "";
        String sealId = "";
        String userId = "";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("sign_id", signId);
        requestBody.put("is_contract_finish", 0);
        requestBody.put("is_render", 0);
        // 合同id
        requestBody.put("contract_id", contractId);
        // 印章id
        requestBody.put("seal_id", sealId);
        // 关键字
        requestBody.put("keyword", "联系电话");
        // 印章宽度
        requestBody.put("width", SealUtils.transitionSizeToPixel(80.0));
        // 印章高度
        requestBody.put("height", SealUtils.transitionSizeToPixel(80.0));
        // true：合同内所有匹配位置全部签署；false：只签署第一个匹配；默认false
        requestBody.put("sign_all", false);
        // 用户id
        requestBody.put("user_id", userId);

        // 执行签章
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/event_cert_sign/keywordSign");// 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
        HashMap<String, Object> signModule = (HashMap<String, Object>) execute.getData();
        boolean signState = (boolean) signModule.get("sign_state");
        if(!signState){
            //失败原因
            String reason = String.valueOf(signModule.get("reason"));
            log.info("失败原因:{}",reason);
        }

    }


    /**
     * 创建签名域信息
     *
     * @param userId
     * @param sealId
     * @return
     */
    private List<HashMap<String, Object>> createSignParams(String userId, String sealId) {
        // 创建签名域签章集合
        List<HashMap<String, Object>> signParams = new ArrayList<>();
        // 创建一个签名域信息
        HashMap<String, Object> e = new HashMap<>();
        e.put("height", SealUtils.transitionSizeToPixel(100d));
        e.put("width", SealUtils.transitionSizeToPixel(100d));
        e.put("seal_id", sealId);
        e.put("sign_key", "sign1");
        e.put("user_id", userId);
        e.put("is_picture", false);
        signParams.add(e);
        // 可以添加多个签名域信息
        // ............

        return signParams;
    }

    private List<HashMap<String, Object>> createTextParams() {
        List<HashMap<String, Object>> textParams = new ArrayList<>();
        // 创建文字域对象
        HashMap<String, Object> e = new HashMap<>();
        e.put("key", "fill_name");
        e.put("value", "张三");
        // 添加文字域对象
        textParams.add(e);
        // 可以添加多个文字域域信息
        // ............


        return textParams;
    }


}
