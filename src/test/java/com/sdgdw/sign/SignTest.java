package com.sdgdw.sign;

import com.sdgdw.sign.bean.CustomSignFields;
import com.sdgdw.sign.bean.SignFields;
import com.sdgdw.sign.bean.SignParam;
import com.sdgdw.sign.bean.TextParam;
import com.sdgdw.sign.module.request.*;
import com.sdgdw.sign.module.response.BatchTemplateResponse;
import com.sdgdw.sign.module.response.MeetingSignResponse;
import com.sdgdw.sign.module.response.SignResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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
        textParam.setValue("測試");
        List<TextParam> textParams = Collections.singletonList(textParam);

        // 签名域信息
        SignParam signParam = new SignParam();
        // 用户id
        signParam.setUserId("00710516447415783424");
        // 印章id
        signParam.setSealId("a9d3a96129e176aaf7a3aa3d006f26da");
        // 签名域key值
        signParam.setSignKey("signature1");
        // 印章宽度
        signParam.setWidth(2.0);
        // 印章高度
        signParam.setHeight(2.0);
        List<SignParam> signParams = Collections.singletonList(signParam);

        SingleTemplateRequest singleTemplateRequest = new SingleTemplateRequest();
        // 模板id
        singleTemplateRequest.setTemplateId("2551b72f248aac89f59a789f72c0e735");
        // 签名域信息
        SignFields signFields = new SignFields();
        // 文本域信息
        signFields.setTextParams(textParams);
        // 签名域信息
        signFields.setSignParams(signParams);
        // 签章信息
        singleTemplateRequest.setSignField(signFields);
        SignResponse execute = signClient.execute(singleTemplateRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 批量模板签章
     */
    @Test
    public void templateBatch() {
        // 签名域信息
        SignParam signParam = new SignParam();
        // 用户id
        signParam.setUserId("00710516447415783424");
        // 印章id
        signParam.setSealId("a9d3a96129e176aaf7a3aa3d006f26da");
        // 签名域key值
        signParam.setSignKey("signature1");
        // 印章宽度
        signParam.setWidth(2.0);
        // 印章高度
        signParam.setHeight(2.0);
        List<SignParam> signParams = Collections.singletonList(signParam);

        // 文本域信息
        TextParam textParam = new TextParam();
        // 签章域key
        textParam.setKey("fill_1");
        // 签章域value
        textParam.setValue("測試");
        List<TextParam> textParams = Collections.singletonList(textParam);

        // 自定义签章参数
        CustomSignFields customSignFields = new CustomSignFields();
        // 自定义参数
        customSignFields.setCustomId("xxxx");
        // 签名域信息
        customSignFields.setSignParams(signParams);
        // 文本域信息
        customSignFields.setTextParams(textParams);

        List<CustomSignFields> customSignFieldsList = Collections.singletonList(customSignFields);

        // 批量签章参数
        BatchTemplateRequest batchTemplateRequest = new BatchTemplateRequest();
        // 模板id
        batchTemplateRequest.setTemplateId("2551b72f248aac89f59a789f72c0e735");
        // 批量签章参数
        batchTemplateRequest.setBatchTemplates(customSignFieldsList);

        BatchTemplateResponse execute = signClient.execute(batchTemplateRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 普通签章
     */
    @Test
    public void common() {
        CommonSignRequest commonSignRequest = new CommonSignRequest();
        // 合同id
        commonSignRequest.setContractId("066262cf6f113e839f00c7391df268b2");
        // 签章备注
        commonSignRequest.setRemark("测试");
        // 签章信息集合
        CommonSignRequest.SignParams signParam = new CommonSignRequest.SignParams();
        // 印章id
        signParam.setSealId("a9d3a96129e176aaf7a3aa3d006f26da");
        // 第几页
        signParam.setPageNumber(1);
        // 水平横坐标
        signParam.setHorizontal(2.0);
        // 垂直纵坐标
        signParam.setVertical(1.0);
        // 印章宽度
        signParam.setWidth(2.0);
        // 印章高度
        signParam.setHeight(2.0);
        List<CommonSignRequest.SignParams> signParams = Collections.singletonList(signParam);
        commonSignRequest.setFields(signParams);
        // 用户id
        commonSignRequest.setUserId("00712322098529980416");

        SignResponse execute = signClient.execute(commonSignRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 关键字签章
     */
    @Test
    public void keywordSign() {
        KeywordSignRequest keywordSignRequest = new KeywordSignRequest();
        // 合同id
        keywordSignRequest.setContractId("eb3c533dd0aaf0ae56f6e255f370cee0");
        // 印章id
        keywordSignRequest.setSealId("a9d3a96129e176aaf7a3aa3d006f26da");
        // 关键字
        keywordSignRequest.setKeyword("xxx");
        // 印章宽度
        keywordSignRequest.setWidth(2.0);
        // 印章高度
        keywordSignRequest.setHeight(2.0);
        // true：合同内所有匹配位置全部签署；false：只签署第一个匹配；默认false
        keywordSignRequest.setSignAll(true);
        // 用户id
        keywordSignRequest.setUserId("00712322098529980416");
        SignResponse execute = signClient.execute(keywordSignRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 会签
     */
    @Test
    public void single() {
        MeetingSignRequest meetingSignRequest = new MeetingSignRequest();
        // 合同id
        meetingSignRequest.setContractId("eb3c533dd0aaf0ae56f6e255f370cee0");
        // 验签详情信息
        MeetingSignRequest.SignDetail signDetail = new MeetingSignRequest.SignDetail();
        // 印章id
        signDetail.setSealId("a9d3a96129e176aaf7a3aa3d006f26da");
        // 用户id
        signDetail.setUserId("00712322098529980416");
        // 页码
        signDetail.setPageNum(1);
        // 规格
        signDetail.setSignSize("10");
        // 签署距离合同上方距离
        signDetail.setSignTop(1);
        // 签署距离合同左方距离
        signDetail.setSignLeft(1);
        MeetingSignResponse execute = signClient.execute(meetingSignRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }
}
