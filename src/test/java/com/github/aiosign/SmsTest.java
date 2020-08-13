package com.github.aiosign;

import com.github.aiosign.enums.SmsType;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 侯存路
 * @description 发送短信
 * @since 2020/8/10 15:42
 */
@Slf4j
public class SmsTest extends AbstractSignTest {


    /**
     * 单次发送短信
     */
    @Test
    public void single() {

        SmsSingleRequest singleRequest = new SmsSingleRequest();
        singleRequest.setContractName("合同名称");
        singleRequest.setUserName("用户名称");
        singleRequest.setPhone("15688413831");
        singleRequest.setSmsType(SmsType.sign_finish);

        SmsSingleResponse execute = signClient.execute(singleRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());

    }


    /**
     * 批量发送短信
     */
    @Test
    public void batch() {

        SmsBatchRequest singleRequest = new SmsBatchRequest();
        singleRequest.setSmsType(SmsType.sign_finish);

        List<SmsBatchRequest.SendInfo> params = new ArrayList<>();
        params.add(new SmsBatchRequest.SendInfo("15688413831", "用户名称1", "合同名称1"));
        params.add(new SmsBatchRequest.SendInfo("15688413831", "用户名称2", "合同名称2"));
        singleRequest.setParams(params);


        SmsBatchResponse execute = signClient.execute(singleRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());

    }


    /**
     * 批量下发短信验证码
     */
    @Test
    public void authCode() {


        SmsAuthCodeRequest singleRequest = new SmsAuthCodeRequest();

        List<SmsAuthCodeRequest.Phone> phones = new ArrayList<>();
        phones.add(new SmsAuthCodeRequest.Phone("123456789", "15688413831"));

        singleRequest.setPhones(phones);
        SmsAuthCodeResponse execute = signClient.execute(singleRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());

    }


    /**
     * 验证验证码
     */
    @Test
    public void validAuthCode() {
        SmsValidAuthCodeRequest request = new SmsValidAuthCodeRequest();
        request.setAuthCode("518808");
        request.setPhone("15688413831");
        request.setUuid("1dc767d8729c48e493a7f34a72b430ff");

        SmsValidAuthCodeResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());

    }

}
