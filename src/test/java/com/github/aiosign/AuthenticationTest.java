package com.github.aiosign;

import com.github.aiosign.base.FileItem;
import com.github.aiosign.bean.Picture;
import com.github.aiosign.enums.CertifyType;
import com.github.aiosign.enums.PictureType;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * @author 侯存路
 * @description 认证
 * @since 2020/8/11 9:54
 */
@Slf4j
public class AuthenticationTest extends AbstractSignTest {


    /**
     * 加密请求下三网认证
     */
    @Test
    public void encryQuery() {

        AuthenticationEncryQueryRequest request = new AuthenticationEncryQueryRequest();
        request.setIdCard("身份证号");
        request.setName("姓名");
        request.setPhone("手机号");


        AuthenticationEncryQueryResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 加密进行银行卡四要素认证
     */
    @Test
    public void blankFourEnCeryQuery() {

        AuthenticationBlankFourEnCeryQueryRequest request = new AuthenticationBlankFourEnCeryQueryRequest();
        request.setIdcard("370126199410075616");
        request.setRealname("魏帅");
        request.setMobile("15864010711");
        request.setBankcard("6212261602023398458");

        AuthenticationBlankFourEnCeryQueryResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 企业三要素认证
     */
    @Test
    public void enterpriseThreeQuery() {

        AuthenticationEnterpriseThreeQueryRequest request = new AuthenticationEnterpriseThreeQueryRequest();
        request.setKeyword("注册号/统一社会信用代码(注册号支持15位，统一社会信用代码支持18位)");
        request.setName("企业名称");
        request.setOpername("企业法人");

        AuthenticationEnterpriseThreeQueryResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 企业四要素认证
     */
    @Test
    public void enterpriseFourElementsQuery() {

        SJBEntFourRequest request = new SJBEntFourRequest();
        request.setName("马宇1");
        request.setIdcard("211021196703110011");
        request.setEntname("山东国盾网信息科技有限公司");
        request.setEntmark("91370100MA3C9NHDXT");

        SJBEntFourResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 实人认证
     */
    @Test
    public void realReople() {

        AuthenticationRPMinRequest request = new AuthenticationRPMinRequest();
        request.setFaceImage(new Picture(PictureType.Url, "http://www.baidu.com"));
        request.setIdCardBackImage(new Picture(PictureType.Url, "http://www.baidu.com"));
        request.setIdCardFrontImage(new Picture(PictureType.Url, "http://www.baidu.com"));
        request.setIdCardNumber("123456789");
        request.setName("小明");

        AuthenticationRPMinResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 阿里认证-开始认证
     */
    @Test
    public void aliResult() {

        AuthenticationAliPayCertifyRequest request = new AuthenticationAliPayCertifyRequest();
        request.setIdCardNum("123456");
        request.setName("名称");
        request.setCertifytype(CertifyType.CERT_PHOTO);

        AuthenticationAliPayCertifyResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 阿里认证-认证查询
     */
    @Test
    public void selectAlipay() {

        AuthenticationAliPayQueryRequest request = new AuthenticationAliPayQueryRequest();
        request.setCertiftyId("123456");

        AuthenticationAliPayQueryResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 企业打款
     */
    @Test
    public void enterprisePay() {

        EnterprisePayReq request = new EnterprisePayReq();
        request.setKeyType("123");
        request.setKey("123");
        request.setName("123");
        request.setUsrName("123");
        request.setAccountNo("123");
        request.setAccountBank("123");
        request.setAccountCity("123");
        request.setAccountProvince("123");

        EnterprisePayResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 企业打款校验
     */
    @Test
    public void ChinaPayEnterprisePayValidReq() {

        ChinaPayEnterprisePayValidReq request = new ChinaPayEnterprisePayValidReq();
        request.setAccountNo("企业银行账户");
        request.setAmount("金额");
        request.setOrderId("打款流水Id");

        ChinaPayEnterprisePayValidResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

    }


    /**
     * 获取百度身份验证-语音验证数据
     */
    @Test
    public void voiceVerificationData() {
        BaiduSessionCodeRequest request = new BaiduSessionCodeRequest();
        BaiduSessionCodeResponse execute = signClient.execute(request);

        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }


    /**
     * 获取百度身份验证-语音验证数据
     */
    @Test
    public void baiDuAiFaceVideoVerify() {
        BaiDuAiFaceVideoVerifyRequest request = new BaiDuAiFaceVideoVerifyRequest();
        request.setFile(new FileItem(new File("E:\\9623ef8f85030bf1f041c8beb62ba2af.jpeg")));
        request.setSessionId("123");

        BaiDuAiFaceVideoVerifyRequestResponse execute = signClient.execute(request);

        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
    }


    /**
     * 获取百度身份验证-验证
     */
    @Test
    public void baiDuAiFaceCertifyVerify() {
        BaiDuAiFaceCertifyVerifyRequest request = new BaiDuAiFaceCertifyVerifyRequest();
        request.setIdCardNumber("string");
        request.setName("string");
        request.setImage("string");
        request.setImageType("string");

        BaiDuAiFaceCertifyVerifyResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
    }


}
