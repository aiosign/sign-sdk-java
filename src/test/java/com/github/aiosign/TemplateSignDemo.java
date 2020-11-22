package com.github.aiosign;

import com.github.aiosign.base.FileItem;
import com.github.aiosign.bean.SignFields;
import com.github.aiosign.bean.SignParam;
import com.github.aiosign.bean.TextParam;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WeiShuai
 * @Date 2020/9/27 0027 下午 05:12
 */
@Slf4j
public class TemplateSignDemo {
    protected SignClient signClient;

    /**
     * 初始化客户端
     */
    @Before
    public void init() {
        signClient = new DefaultSignClient("https://open.aiosign.com/api", "729742355062214656",
                "VBXdvJLpOBjViUEPRt");
    }

    @Test
    public void TemplateSign() throws FileNotFoundException {
        //合同签署前准备中 获取的UserId / sealId / templateId..... 等 均可重复使用.........


        //  ************************************合同签署前准备******************************
        // 1. 用户注册 依据实际情况注册个人或企业用户
        //      个人用户注册（注册完成后用户可长期使用）
        UserPersonalRegisterResponse.UserPersonalRegisterModule personal = register1();
        //      获取个人用户id
        String userId = personal.getUserId();
//        //      企业用户注册（注册完成后用户可长期使用）
//        UserCompanyResponse.UserCompanyModule company = register2();
//        //      获取userId
//        //      企业用户ID
//        String userId = company.getUserId();

        // 2.上传印章文件及创建印章
        SealAddAndFileResponse seal = uploadImpression("演示印章", userId);
        //    获取上传完成后印章id
        String sealId = seal.getSealId();
        // 3.上传模板文件及创建模板
        TemplateAddAndFileResponse templateAddAndFileResponse = fileUploadAndTemplateCreation();
        //    获取模板id
        String templateId = templateAddAndFileResponse.getTemplateId();
        //  ************************************合同签署前准备******************************


        //  *************************************开始签署**********************************
        EventCertSignTemplateRequest signReq = new EventCertSignTemplateRequest();
        // 1.写入需要使用的模板id
        signReq.setTemplateId(templateId);
        // 创建隐藏域信息
        SignFields signField = new SignFields();

        // 2.创建签名域信息
        List<SignParam> signParams = createSignParams(userId, sealId);
        // 写入签名域信息
        signField.setSignParams(signParams);
        // 3.创建文字域信息
        List<TextParam> textParams = createTextParams();
        // 写入文字域信息
        signField.setTextParams(textParams);

        // 放入签名域与文字域信息
        signReq.setSignField(signField);
        // 执行签章
        SignResponse execute = signClient.execute(signReq);
        // 签章结果
        boolean result = execute.isSuccess();
        log.info("签章结果为：" + result);
        // 签章完成，下载签署后文件
        if (result) {
            downloadFile(execute.getData().getFileId(), new FileOutputStream("C:\\Users\\Administrator\\Documents\\签署完成文件.pdf"));
        }
        log.info("签署完成");
        //  *************************************签署结束**********************************

    }

    public void downloadFile(String fileId, OutputStream outputStream) throws FileNotFoundException {
        String baseUri = "/v1/file/download";
        signClient.download(baseUri, fileId, outputStream);
    }

    private List<TextParam> createTextParams() {
        List<TextParam> textParams = new ArrayList<>();
        // 创建文字域对象
        TextParam e = new TextParam();
        e.setKey("fill_name");
        e.setValue("张三");
        // 添加文字域对象
        textParams.add(e);
        // 可以添加多个文字域域信息
        // ............


        return textParams;
    }

    /**
     * 创建签名域信息
     *
     * @param userId
     * @param sealId
     * @return
     */
    private List<SignParam> createSignParams(String userId, String sealId) {
        // 创建签名域签章集合
        List<SignParam> signParams = new ArrayList<>();
        // 创建一个签名域信息
        SignParam e = new SignParam();
        e.setHeight(100d);
        e.setWidth(100d);
        e.setSealId(sealId);
        e.setSignKey("sign1");
        e.setUserId(userId);
        e.setIsPicture(false);
        signParams.add(e);
        // 可以添加多个签名域信息
        // ............

        return signParams;
    }

    /**
     * 文件上传与模板创建
     *
     * @return
     */
    @Test
    public TemplateAddAndFileResponse fileUploadAndTemplateCreation() {
        TemplateAddAndFileRequest request = new TemplateAddAndFileRequest();
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Documents\\测试合同.pdf"));
        request.setFileItem(fileItem);
        request.setFileName("测试合同");
        request.setName("测试模板");
        TemplateAddAndFileResponse templateAddAndFileResponse = signClient.execute(request);
        log.info("响应状态：{}", templateAddAndFileResponse.getResultCode());
        log.info("响应信息：{}", templateAddAndFileResponse.getResultMessage());
        log.info("响应数据：{}", templateAddAndFileResponse.getFileId());
        log.info("响应数据：{}", templateAddAndFileResponse.getTemplateId());
        return templateAddAndFileResponse;
    }

    @Test
    public SealAddAndFileResponse uploadImpression(String sealName, String userId) {
        SealAddAndFileRequest request = new SealAddAndFileRequest();
        request.setFileName("download.png");
        request.setUserId(userId);
        FileItem fileItem = new FileItem(new File("C:\\Users\\Administrator\\Downloads\\download.png"));
        request.setFileItem(fileItem);
        request.setSealName(sealName);
        // 印章类型
        //01	单位专用章
        //02	财务专用章
        //03	发票专用章
        //04	合同专用章
        //05	法人名章
        //99	其它
        request.setSealType("99");
        // 印章size  单位：MM
        request.setSize("40*40");
        request.setDescription("测试");

        SealAddAndFileResponse response = signClient.execute(request);
        log.info("响应状态：{}", response.getResultCode());
        log.info("响应信息：{}", response.getResultMessage());
        log.info("响应数据：{}", response.getFileId());
        log.info("响应数据：{}", response.getSealId());
        return response;
    }


    /**
     * 个人用户注册
     *
     * @return
     */
    @Test
    public UserPersonalRegisterResponse.UserPersonalRegisterModule register1() {
        UserPersonalRegisterRequest userPersonalLockRequest = new UserPersonalRegisterRequest();
        // 姓名
        userPersonalLockRequest.setUserName("测试001");
        // 地区编码
        userPersonalLockRequest.setAreaCode("370101");
        // 手机号
        userPersonalLockRequest.setPhone("15684532141");
        // 证件类型
        userPersonalLockRequest.setIdType("111");
        // 证件号
        userPersonalLockRequest.setIdNumber("371525199309870986");
        // 邮箱地址
        userPersonalLockRequest.setMail("1562310354@qq.com");
        // 描述
        userPersonalLockRequest.setDescription("测试001");
        UserPersonalRegisterResponse execute = signClient.execute(userPersonalLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
        return execute.getData();
    }

    /**
     * 企业用户注册
     *
     * @return
     */
    @Test
    public UserCompanyResponse.UserCompanyModule register2() {
        UserCompanyRegisterRequest userCompanyRegisterRequest = new UserCompanyRegisterRequest();
        // 企业名称
        userCompanyRegisterRequest.setUserName("测试法人用户001");
        // 地区编码
        userCompanyRegisterRequest.setAreaCode("370101");
        // 单位类型
        userCompanyRegisterRequest.setUnitType("03");
        // 统一社会信用代码
        userCompanyRegisterRequest.setCrediCode("676543456789875432");
        // 法人名称
        userCompanyRegisterRequest.setLegalName("测试法人用户");
        // 法人证件号
        userCompanyRegisterRequest.setLegalIdNumber("371314288798766356");
        // 法人证件类型
        userCompanyRegisterRequest.setLegalIdType("111");
        // 法人电话
        userCompanyRegisterRequest.setLegalPhone("19876545654");
        // 法人邮箱地址
        userCompanyRegisterRequest.setLegalEmail("12388373@qq.com");
        // 经办人姓名
        userCompanyRegisterRequest.setAgentName("测试经办人");
        // 经办人证件号
        userCompanyRegisterRequest.setAgentIdNumber("326565678765465789");
        // 经办人证件类型
        userCompanyRegisterRequest.setAgentIdType("111");
        // 经办人电话
        userCompanyRegisterRequest.setAgentPhone("15609876754");
        // 经办人邮箱
        userCompanyRegisterRequest.setAgentEmail("18762873638@qq.com");
        // 描述
        userCompanyRegisterRequest.setDescription("测试企业用户001");
        UserCompanyResponse execute = signClient.execute(userCompanyRegisterRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
        return execute.getData();
    }

    public static void main(String[] args) {
        SignClient signClient = new DefaultSignClient("https://open.aiosign.com/api", "731266365780545536",
                "UqhqrggjBErJKUeyaV");


        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/seal/apply-seal");
        request.setContentType(ContentType.JSON);
        request.setMethod(HttpMethod.POST);
        request.setNeedToken(true);
        request.setRequestBody("{}");
        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
