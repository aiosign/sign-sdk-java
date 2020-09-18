package com.github.aiosign;

import com.github.aiosign.bean.CustomSignFields;
import com.github.aiosign.bean.SignFields;
import com.github.aiosign.bean.SignParam;
import com.github.aiosign.bean.TextParam;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.BatchTemplateResponse;
import com.github.aiosign.module.response.DirectSignResponse;
import com.github.aiosign.module.response.MeetingSignResponse;
import com.github.aiosign.module.response.SignResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * @author yangyouwang
 * @description 合同签章
 * @since 2020/5/13
 */
@Slf4j
public class SignTest extends AbstractSignTest {


    /**
     * 单次模板签章
     */
    @Test
    public void templateSingle() {
        // 文本域信息
        TextParam textParam = new TextParam();
        // 签章域key
        textParam.setKey("fill_1");
        // 签章域value
        textParam.setValue("测试");
        List<TextParam> textParams = Collections.singletonList(textParam);

        // 签名域信息
        SignParam signParam = new SignParam();
        // 用户id
        signParam.setUserId("00716661208384163840");
        // 印章id
        signParam.setSealId("a9e48474650448709a3b577ce4f72234");
        // 签名域key值
        signParam.setSignKey("signature1");
        // 印章宽度
        signParam.setWidth(50.0);
        // 印章高度
        signParam.setHeight(50.0);
        List<SignParam> signParams = Collections.singletonList(signParam);

        SingleTemplateRequest singleTemplateRequest = new SingleTemplateRequest();
        // 模板id
        singleTemplateRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        // 签名域信息
        SignFields signFields = new SignFields();
        // 文本域信息
        signFields.setTextParams(textParams);
        // 签名域信息
        signFields.setSignParams(signParams);
        // 签章信息
        singleTemplateRequest.setSignField(signFields);
        SignResponse execute = signClient.execute(singleTemplateRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 批量模板签章
     */
    @Test
    public void templateBatch() {
        // 签名域信息
        SignParam signParam = new SignParam();
        // 用户id
        signParam.setUserId("00716661208384163840");
        // 印章id
        signParam.setSealId("a9e48474650448709a3b577ce4f72234");
        // 签名域key值
        signParam.setSignKey("signature1");
        // 印章宽度
        signParam.setWidth(27.9);
        // 印章高度
        signParam.setHeight(28.0);
        List<SignParam> signParams = Collections.singletonList(signParam);

        // 文本域信息
        TextParam textParam = new TextParam();
        // 签章域key
        textParam.setKey("fill_1");
        // 签章域value
        textParam.setValue("测试");
        List<TextParam> textParams = Collections.singletonList(textParam);

        // 自定义签章参数
        CustomSignFields customSignFields = new CustomSignFields();
        // 自定义参数
        customSignFields.setCustomId("28929298383883812");
        // 签名域信息
        customSignFields.setSignParams(signParams);
        // 文本域信息
        customSignFields.setTextParams(textParams);

        List<CustomSignFields> customSignFieldsList = Collections.singletonList(customSignFields);

        // 批量签章参数
        BatchTemplateRequest batchTemplateRequest = new BatchTemplateRequest();
        // 模板id
        batchTemplateRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        // 批量签章参数
        batchTemplateRequest.setBatchTemplates(customSignFieldsList);

        BatchTemplateResponse execute = signClient.execute(batchTemplateRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 普通签章
     */
    @Test
    public void common() {
        CommonSignRequest commonSignRequest = new CommonSignRequest();
        // 合同id
        commonSignRequest.setContractId("43e52182c47889d2fe370c6a2228e9a3");
        // 签章备注
        commonSignRequest.setRemark("测试普通签章");
        // 签章信息集合
        CommonSignRequest.SignParams signParam = new CommonSignRequest.SignParams();
        // 印章id
        signParam.setSealId("a9e48474650448709a3b577ce4f72234");
        // 第几页
        signParam.setPageNumber(1);
        // 水平横坐标
        signParam.setHorizontal(20.9);
        // 垂直纵坐标
        signParam.setVertical(28.8);
        // 印章宽度
        signParam.setWidth(40.0);
        // 印章高度
        signParam.setHeight(40.0);
        List<CommonSignRequest.SignParams> signParams = Collections.singletonList(signParam);
        commonSignRequest.setFields(signParams);
        // 用户id
        commonSignRequest.setUserId("00716661208384163840");

        SignResponse execute = signClient.execute(commonSignRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 关键字签章
     */
    @Test
    public void keywordSign() {
        KeywordSignRequest keywordSignRequest = new KeywordSignRequest();
        // 合同id
        keywordSignRequest.setContractId("43e52182c47889d2fe370c6a2228e9a3");
        // 印章id
        keywordSignRequest.setSealId("a9e48474650448709a3b577ce4f72234");
        // 关键字
        keywordSignRequest.setKeyword("联系电话");
        // 印章宽度
        keywordSignRequest.setWidth(80.0);
        // 印章高度
        keywordSignRequest.setHeight(80.0);
        // true：合同内所有匹配位置全部签署；false：只签署第一个匹配；默认false
        keywordSignRequest.setSignAll(false);
        // 用户id
        keywordSignRequest.setUserId("00716661208384163840");
        SignResponse execute = signClient.execute(keywordSignRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 会签
     */
    @Test
    public void single() {
        MeetingSignRequest meetingSignRequest = new MeetingSignRequest();
        // 合同id
        meetingSignRequest.setContractId("43e52182c47889d2fe370c6a2228e9a3");
        // 验签详情信息
        MeetingSignRequest.SignDetail signDetail = new MeetingSignRequest.SignDetail();
        // 印章id
        signDetail.setSealId("a9e48474650448709a3b577ce4f72234");
        // 用户id
        signDetail.setUserId("00716661208384163840");
        // 页码
        signDetail.setPageNum(1);
        // 规格
        signDetail.setSignSize("10");
        // 签署距离合同上方距离
        signDetail.setSignTop(100);
        // 签署距离合同左方距离
        signDetail.setSignLeft(100);
        meetingSignRequest.setSignDetails(Collections.singletonList(signDetail));
        MeetingSignResponse execute = signClient.execute(meetingSignRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 一部签署
     */
    @Test
    public void directSign() throws IOException {
        DirectSignRequest directSignRequest = new DirectSignRequest();
        //用户类型
        directSignRequest.setUserType(1);
        //用户名称
        directSignRequest.setUserName("测试人");
        //身份证号码
        directSignRequest.setIdNumber("371525199309870986");
        String contractPath="/Users/modificial/Downloads/1.pdf";
        byte[] bytes = Files.readAllBytes(Paths.get(contractPath));
        //base64合同文件
        directSignRequest.setContractFileContent(Base64.getEncoder().encodeToString(bytes));
        List<DirectSignRequest.SignDetail> signFields = new ArrayList<>();
        DirectSignRequest.SignDetail signDetail = new DirectSignRequest.SignDetail();
        String sealPath="/Users/modificial/Downloads/出证专用章.png";
        byte[] sealBytes = Files.readAllBytes(Paths.get(sealPath));
        //base64 印章文件
        signDetail.setSealFileContent(Base64.getEncoder().encodeToString(sealBytes));
        //签章需要的x坐标
        signDetail.setHorizontal(40.0);
        //签章需要的y坐标
        signDetail.setVertical(40.0);
        //签章页数
        signDetail.setPageNum(1);
        //印章高度
        signDetail.setSealHeight(50.0);
        //印章宽度
        signDetail.setSealWidth(50.0);
        signFields.add(signDetail);
        directSignRequest.setSignFields(signFields);
        DirectSignResponse execute = signClient.execute(directSignRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
