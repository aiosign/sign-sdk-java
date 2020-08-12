package com.github.aiosign;

import com.github.aiosign.bean.Picture;
import com.github.aiosign.enums.CertifyType;
import com.github.aiosign.enums.PictureType;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
        request.setIdcard("身份证号");
        request.setRealname("姓名");
        request.setMobile("手机号");
        request.setBankcard("银行卡");

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


}
