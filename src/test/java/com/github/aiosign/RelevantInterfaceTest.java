package com.github.aiosign;

import com.github.aiosign.base.FileItem;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * @author 侯存路
 * @description 相关业务 组合接口
 * @since 2020/6/15 18:59
 */
@Slf4j
public class RelevantInterfaceTest extends AbstractSignTest {


    /**
     * 个人用户注册 与 用户证书申请
     */
    @Test
    public void userPersonalRegisterCert() {
        UserPersonalRegisterCertRequest userPersonalRegisterCertRequest = new UserPersonalRegisterCertRequest();

        UserPersonalRegisterRequest uprcr = new UserPersonalRegisterRequest();
        // 姓名
        uprcr.setUserName("测试1111111111");
        // 地区编码
        uprcr.setAreaCode("370101");
        // 手机号
        uprcr.setPhone("15684532141");
        // 证件类型
        uprcr.setIdType("111");
        // 证件号
        uprcr.setIdNumber("371525199878650986");
        // 邮箱地址
        uprcr.setMail("1562310354@qq.com");
        // 描述
        uprcr.setDescription("测试001555");
        userPersonalRegisterCertRequest.setUserPersonalRegisterRequest(uprcr);


        UserPersonalRegisterCertResponse registerCertResponse = signClient.execute(userPersonalRegisterCertRequest);
        log.info("响应状态：{}", registerCertResponse.getResultCode());
        log.info("响应信息：{}", registerCertResponse.getResultMessage());
        log.info("响应数据：{}", registerCertResponse.getData());
        log.info("响应数据：{}", registerCertResponse.getUserId());
    }


    /**
     * 企业用户注册 与 用户证书申请
     */
    @Test
    public void userCompanyRegisterCert() {
        UserCompanyRegisterCertRequest userCompanyRegisterCertRequest = new UserCompanyRegisterCertRequest();


        UserCompanyRegisterRequest userCompanyRegisterRequest = new UserCompanyRegisterRequest();
        // 企业名称
        userCompanyRegisterRequest.setUserName("测试");
        // 地区编码
        userCompanyRegisterRequest.setAreaCode("370101");
        // 单位类型
        userCompanyRegisterRequest.setUnitType("1");
        // 统一社会信用代码
        userCompanyRegisterRequest.setCrediCode("xxx");
        // 法人名称
        userCompanyRegisterRequest.setLegalName("xxx");
        // 法人证件号
        userCompanyRegisterRequest.setLegalIdNumber("xxx");
        // 法人证件类型
        userCompanyRegisterRequest.setLegalIdType("xxx");
        // 法人电话
        userCompanyRegisterRequest.setLegalPhone("156xxxxxxxx");
        // 法人邮箱地址
        userCompanyRegisterRequest.setLegalEmail("xxx@qq.com");
        // 经办人姓名
        userCompanyRegisterRequest.setAgentName("xxx");
        // 经办人证件号
        userCompanyRegisterRequest.setAgentIdNumber("xxx");
        // 经办人证件类型
        userCompanyRegisterRequest.setAgentIdType("xxx");
        // 经办人电话
        userCompanyRegisterRequest.setAgentPhone("156xxxxxxxxx");
        // 经办人邮箱
        userCompanyRegisterRequest.setAgentEmail("xxx@qq.com");
        // 描述
        userCompanyRegisterRequest.setDescription("测试");

        userCompanyRegisterCertRequest.setUserCompanyRegisterRequest(userCompanyRegisterRequest);

        UserCompanyRegisterCertResponse registerCertResponse = signClient.execute(userCompanyRegisterCertRequest);
        log.info("响应状态：{}", registerCertResponse.getResultCode());
        log.info("响应信息：{}", registerCertResponse.getResultMessage());
        log.info("响应数据：{}", registerCertResponse.getData());
        log.info("响应数据：{}", registerCertResponse.getUserId());
    }


    /**
     * 文件上传与印章添加
     */
    @Test
    public void fileUploadAndSealAddition() {
        SealAddAndFileRequest request = new SealAddAndFileRequest();
        request.setFileName("测试印章");
        request.setUserId("00715229845080657920");
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Downloads\\download.png"));
        request.setFileItem(fileItem);
        request.setSealName("测试印章");
        request.setSealType("99");
        request.setSize("40*40");
        request.setDescription("测试");

        SealAddAndFileResponse response = signClient.execute(request);
        log.info("响应状态：{}", response.getResultCode());
        log.info("响应信息：{}", response.getResultMessage());
        log.info("响应数据：{}", response.getFileId());
        log.info("响应数据：{}", response.getSealId());
    }


    /**
     * 文件上传与模板创建
     */
    @Test
    public void fileUploadAndTemplateCreation() {
        TemplateAddAndFileRequest request = new TemplateAddAndFileRequest();
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Desktop\\PaaS\\滨州项目\\阿里巴巴Java开发手册终极版v1.3.0.pdf"));
        request.setFileItem(fileItem);
        request.setFileName("测试合同");
        request.setName("测试模板");

        TemplateAddAndFileResponse templateAddAndFileResponse = signClient.execute(request);
        log.info("响应状态：{}", templateAddAndFileResponse.getResultCode());
        log.info("响应信息：{}", templateAddAndFileResponse.getResultMessage());
        log.info("响应数据：{}", templateAddAndFileResponse.getFileId());
        log.info("响应数据：{}", templateAddAndFileResponse.getTemplateId());
    }


    /**
     * 文件上传与合同创建
     */
    @Test
    public void fileUploadAndContractCreation() {

        signClient =  new DefaultSignClient("https://unknow.sdguodun.com:8000/api","722890774874312704","DRDAQOKvmWHSILgSTJ");

        ContractAddAndFileRequest request = new ContractAddAndFileRequest();
        request.setDescription("描述");
        FileItem fileItem = new FileItem(new File("E:\\微信备份\\WeChat Files\\shuai513470\\FileStorage\\File\\2020-11\\票种核定.pdf"));
        request.setFileItem(fileItem);
        request.setFileName("测试合同");
        request.setName("测试合同");
        request.setUserId("10773494534931369984");
        ContractAddAndFileResponse response = signClient.execute(request);
        log.info("响应状态：{}", response.getResultCode());
        log.info("响应信息：{}", response.getResultMessage());
        log.info("响应数据：{}", response.getFileId());
        log.info("响应数据：{}", response.getContractId());
    }


}
