package com.github.aiosign.csh;

import com.alibaba.fastjson.JSON;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import com.github.aiosign.utils.SealSizeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ADun
 * @date 2023/5/6 16:36
 */
@Slf4j
public class CshProcessTests extends AbstractSignTest {

    @Test
    public void cshProcess() throws FileNotFoundException {
        long startTime = System.currentTimeMillis();

        String userId = "10144942136450173440";
        String folderPath = "E:\\worktwo\\csh\\三方签署测试pdf\\";

        long getUserInfoStartTime = System.currentTimeMillis();
        // 获取当前用户信息
        HashMap<String, Object> companyUserInfoRequestBody = new HashMap<>();
        // 用户id
        companyUserInfoRequestBody.put("user_id",userId);

        CommonRequest companyUserRequest = new CommonRequest();
        companyUserRequest.setApiUri("/v1/user/company/userinfo");// 请求Api地址
        companyUserRequest.setNeedToken(true);// 是否需要token
        companyUserRequest.setContentType(ContentType.JSON);// 请求头类型
        companyUserRequest.setMethod(HttpMethod.POST);// 请求方法
        companyUserRequest.setRequestBody(companyUserInfoRequestBody);// 请求体
        CommonResponse execute = signClient.execute(companyUserRequest);
        log.info("companyUser响应状态：{}", execute.getResultCode());
        log.info("companyUser响应信息：{}", execute.getResultMessage());
        log.info("companyUser响应数据：{}", execute.getData());
        long getUserInfoEndTime = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", getUserInfoEndTime - getUserInfoStartTime);

        // 获取用户签章信息
        long getSealInfoStartTime = System.currentTimeMillis();
        HashMap<String, String> requestBody = new HashMap<>(2);
        requestBody.put("user_id", userId);

        CommonRequest sealRequest = new CommonRequest();
        sealRequest.setApiUri("/v1/seal/getSealInfos"); // 请求Api地址
        sealRequest.setNeedToken(true);// 是否需要token
        sealRequest.setContentType(ContentType.JSON);// 请求头类型
        sealRequest.setMethod(HttpMethod.POST);// 请求方法
        sealRequest.setRequestBody(requestBody);// 请求体

        CommonResponse sealExecute = signClient.execute(sealRequest);
        log.info("seal响应状态：{}", sealExecute.getResultCode());
        log.info("seal响应信息：{}", sealExecute.getResultMessage());
        log.info("seal响应数据：{}", sealExecute.getData());
        List<HashMap<String, String>> sealInfoModuleList = (List<HashMap<String, String>>) sealExecute.getData();
        HashMap<String, String> sealInfoModule = sealInfoModuleList.get(1);
        String sealId = sealInfoModule.get("seal_id");
        String sealSize = sealInfoModule.get("size");
        String sealFileId = sealInfoModule.get("file_id");
        log.info("sealId：{}", sealId);
        log.info("sealSize：{}", sealSize);
        // 获取印章的宽高
        List<String> sealSizeList = Arrays.asList(sealSize.trim().split("\\*"));
        Double sealWidth = Double.valueOf(sealSizeList.get(0));
        Double sealHigh = Double.valueOf(sealSizeList.get(1));
        log.info("sealWidth:{}", sealWidth);
        log.info("sealHigh:{}", sealHigh);
        log.info("sealFileId：{}", sealFileId);
        long getSealInfoEndTime = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", getSealInfoEndTime - getSealInfoStartTime);


        // 上传文件
        long fileUploadStartTIme = System.currentTimeMillis();
        FileItem fileItem = new FileItem(new File(folderPath + "中国IT服务人才供给报告.pdf"));
        CommonRequest fileUploadRequest = new CommonRequest();
        fileUploadRequest.setApiUri("/v1/file/upload");// 请求Api地址
        fileUploadRequest.setNeedToken(true);// 是否需要token
        fileUploadRequest.setContentType(ContentType.MULTIPART);// 请求头类型
        fileUploadRequest.setMethod(HttpMethod.POST);// 请求方法
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file", fileItem);
        fileUploadRequest.setFileParams(map);
        Map<String, String> params = fileUploadRequest.getParams();
        params.put("file_name", "测试合同");
        params.put("file_type", "contract");
        params.put("user_id", userId);
        CommonResponse fileUploadExecute = signClient.execute(fileUploadRequest);
        log.info("fileUpload响应状态：{}", fileUploadExecute.getResultCode());
        log.info("fileUpload响应信息：{}", fileUploadExecute.getResultMessage());
        log.info("fileUpload响应数据：{}", fileUploadExecute.getData());
        HashMap<String, String> fileUploadModule = (HashMap<String, String>)fileUploadExecute.getData();
        String fileUploadFileId = fileUploadModule.get("file_id");
        String fileUploadFileName = fileUploadModule.get("file_id");
        String fileUploadFileType = fileUploadModule.get("file_type");
        log.info("fileUploadFileId：{}", fileUploadFileId);
        log.info("fileUploadFileName：{}", fileUploadFileName);
        log.info("fileUploadFileType：{}", fileUploadFileType);
        long fileUploadEndTIme = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", fileUploadEndTIme - fileUploadStartTIme);


        long contractAddStartTIme = System.currentTimeMillis();
        // 添加文件为合同
        HashMap<String, Object> contractRequestBody = new HashMap<>();
        // 合同名字
        contractRequestBody.put("name","测试");
        // 文件id
        contractRequestBody.put("file_id",fileUploadFileId);
        // 用户id不能为空
        contractRequestBody.put("user_id",userId);
        // 描述信息
        contractRequestBody.put("description", "测试描述");

        CommonRequest contractRequest = new CommonRequest();
        contractRequest.setApiUri("/v1/contract/add"); // 请求Api地址
        contractRequest.setNeedToken(true);// 是否需要token
        contractRequest.setContentType(ContentType.JSON);// 请求头类型
        contractRequest.setMethod(HttpMethod.POST);// 请求方法
        contractRequest.setRequestBody(contractRequestBody);// 请求体

        CommonResponse contractAddExecute = signClient.execute(contractRequest);
        log.info("contractAdd响应状态：{}", contractAddExecute.getResultCode());
        log.info("contractAdd响应信息：{}", contractAddExecute.getResultMessage());
        log.info("contractAdd响应数据：{}", contractAddExecute.getData());
        HashMap<String,Object> contractAddModule = (HashMap<String,Object>) contractAddExecute.getData();
        String contractId = String.valueOf(contractAddModule.get("contract_id"));
        log.info("contractId：{}", contractId);
        long contractAddEndTIme = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", contractAddEndTIme - contractAddStartTIme);



        long eventSignStartTIme = System.currentTimeMillis();
        HashMap<String, Object> eventCertSignRequestBody = new HashMap<>();
        // 自定义签署ID
        // 生成自定义签署ID
        String signId = UUID.randomUUID().toString().replaceAll("-", "");
        eventCertSignRequestBody.put("sign_id", signId);
        // 是否完结合同
        eventCertSignRequestBody.put("is_contract_finish", 0);
        // 是否渲染页面
        eventCertSignRequestBody.put("is_render", 0);
        // 合同id
        eventCertSignRequestBody.put("contract_id", contractId);
        // 签章备注
        eventCertSignRequestBody.put("remark", "测试");

        ArrayList<Map<String, Object>> signParamList = new ArrayList<>();
        HashMap<String, Object> signParams = new HashMap<>();
        signParams.put("seal_id", sealId);
        // 页码
        signParams.put("page_number", 1);
        // 水平横坐标
        signParams.put("horizontal", 100D);
        // 垂直纵坐标
        signParams.put("vertical", 100D);
        // 印章宽度(使用工具类转为像素)
        signParams.put("width", SealSizeUtils.transitionSizeToPixel(40D));
        // 印章高度(使用工具类转为像素)
        signParams.put("height", SealSizeUtils.transitionSizeToPixel(40D));
        // 印章旋转角度
        signParams.put("rotate", 0.0D);
        // 签章模式
        signParams.put("layout", 1);

        signParamList.add(signParams);
        // 签章信息集合
        eventCertSignRequestBody.put("fields", signParamList);
        // 用户id
        eventCertSignRequestBody.put("user_id", userId);

        CommonRequest eventCertSignRequest = new CommonRequest();
        eventCertSignRequest.setApiUri("/v1/event_cert_sign/common");// 请求Api地址
        eventCertSignRequest.setNeedToken(true);// 是否需要token
        eventCertSignRequest.setContentType(ContentType.JSON);// 请求头类型
        eventCertSignRequest.setMethod(HttpMethod.POST);// 请求方法
        eventCertSignRequest.setRequestBody(eventCertSignRequestBody);// 请求体
        CommonResponse eventCertSignExecute = signClient.execute(eventCertSignRequest);
        log.info("sign响应状态：{}", eventCertSignExecute.getResultCode());
        log.info("sign响应信息：{}", eventCertSignExecute.getResultMessage());
        log.info("sign响应数据：{}", eventCertSignExecute.getData());
        HashMap<String, Object> signModule = (HashMap<String, Object>) eventCertSignExecute.getData();
        String signSignId = String.valueOf(signModule.get("sign_id"));
        String signFileId = String.valueOf(signModule.get("file_id"));
        String signHash = String.valueOf(signModule.get("hash"));
        boolean signState = (boolean) signModule.get("sign_state");
        log.info("signSignId：{}", signSignId);
        log.info("signFileId：{}", signFileId);
        log.info("signHash：{}", signHash);
        log.info("signState:{}",signState);
        long eventSignEndTIme = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", eventSignEndTIme - eventSignStartTIme);

        long meetingComprehensSignStartTime = System.currentTimeMillis();
        // 会议综合签章
        HashMap<String, Object> meetingComprehensSignRequestBody = new HashMap<>();
        // 合同ID
        meetingComprehensSignRequestBody.put("contract_id", contractId);
        // 是否完结
        meetingComprehensSignRequestBody.put("is_contract_finish", 0);

        ArrayList<Map<String, Object>> authSignDetails = new ArrayList<>();
        // 授权人ID
        HashMap<String, Object> authSignDetail = new HashMap<>();
        authSignDetail.put("auth_user_id", userId);
        authSignDetail.put("auth_seal_id",sealId);
        authSignDetail.put("sign_user_id",userId);
        authSignDetail.put("layout",1);
        authSignDetail.put("page_number",1);
        authSignDetail.put("width",SealSizeUtils.transitionSizeToPixel(50.0));
        authSignDetail.put("height",SealSizeUtils.transitionSizeToPixel(50.0));
        authSignDetail.put("horizontal",100);
        authSignDetail.put("vertical",200);
        authSignDetail.put("rotate",0.0D);
        authSignDetails.add(authSignDetail);
        meetingComprehensSignRequestBody.put("auth_sign_details",authSignDetails);

        // 签署信息
        List<Map<String, Object>> signDetails = new ArrayList<>();
        Map<String, Object> signDetail = new HashMap<>();
        // 签署页码
        signDetail.put("page_number", 1);
        // 印章ID
        signDetail.put("seal_id", sealId);
        signDetail.put("user_id", userId);
        signDetail.put("horizontal", 10);
        signDetail.put("vertical", 10);
        signDetail.put("height", SealSizeUtils.transitionSizeToPixel(50.0));
        signDetail.put("width", SealSizeUtils.transitionSizeToPixel(50.0));
        signDetails.add(signDetail);
        meetingComprehensSignRequestBody.put("sign_details", signDetails);

        CommonRequest meetingComprehensSignRequest = new CommonRequest();
        meetingComprehensSignRequest.setApiUri("/v1/sign/meeting/comprehensive");// 请求Api地址
        meetingComprehensSignRequest.setNeedToken(true);// 是否需要token
        meetingComprehensSignRequest.setContentType(ContentType.JSON);// 请求头类型
        meetingComprehensSignRequest.setMethod(HttpMethod.POST);// 请求方法
        meetingComprehensSignRequest.setRequestBody(meetingComprehensSignRequestBody);// 请求体
        CommonResponse meetingComprehensSignExecute = signClient.execute(meetingComprehensSignRequest);
        log.info("会议综合签章响应状态：{}", meetingComprehensSignExecute.getResultCode());
        log.info("会议综合签章响应信息：{}", meetingComprehensSignExecute.getResultMessage());
        log.info("会议综合签章响应数据：{}", meetingComprehensSignExecute.getData());
        HashMap<String, Object> meetingComprehensSignModule = (HashMap<String, Object>) meetingComprehensSignExecute.getData();
        String meetingSignSignId = String.valueOf(meetingComprehensSignModule.get("sign_id"));
        String meetingSignFileId = String.valueOf(signModule.get("file_id"));
        String meetingSignHash = String.valueOf(signModule.get("hash"));
        boolean meetingSignState = (boolean)signModule.get("sign_state");
        log.info("signSignId：{}", meetingSignSignId);
        log.info("signFileId：{}", meetingSignFileId);
        log.info("signHash：{}", meetingSignHash);
        log.info("signState", meetingSignState);
        long meetingComprehensSignEndTime = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", meetingComprehensSignEndTime - meetingComprehensSignStartTime);

        long downloadFileStartTIme = System.currentTimeMillis();
        // 下载签署后的文件
        String baseUri = "/v1/file/download";
        // String fileId = "ceafec7cdfaf4f3b8f7af21966a137e9";
        FileOutputStream out = new FileOutputStream(folderPath + "contract.pdf");
        signClient.download(baseUri, signFileId, out);
        long downloadFileEndTIme = System.currentTimeMillis();
        log.info("下载接口耗时：{}ms\n\n", downloadFileEndTIme - downloadFileStartTIme);

        // 存证报告下载
        String baseUri1 = "/v1/file/downloadReport";
        // String fileId = "5a743d994e45e729961cb520fef590ce";
        FileOutputStream out1 = new FileOutputStream(folderPath + "report.pdf");
        signClient.download(baseUri1, signFileId, out1);
        log.info("存证报告下载完成\n\n");


        long fileIdCheckStartTIme = System.currentTimeMillis();
        // 根据文件id验签
        HashMap<String, Object> fileIdentityRequestBody = new HashMap<>();
        // 文件id
        fileIdentityRequestBody.put("file_id",signFileId);

        CommonRequest fileIdCheckRequest = new CommonRequest();
        fileIdCheckRequest.setApiUri("/v1/sign/check/common"); // 请求Api地址
        fileIdCheckRequest.setNeedToken(true);// 是否需要token
        fileIdCheckRequest.setContentType(ContentType.JSON);// 请求头类型
        fileIdCheckRequest.setMethod(HttpMethod.POST);// 请求方法
        fileIdCheckRequest.setRequestBody(fileIdentityRequestBody);// 请求体

        CommonResponse fileIdCheckExecute = signClient.execute(fileIdCheckRequest);
        log.info("响应状态：{}", fileIdCheckExecute.getResultCode());
        log.info("响应信息：{}", fileIdCheckExecute.getResultMessage());
        log.info("响应数据：{}", fileIdCheckExecute.getData());
        long fileIdCheckEndTIme = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", fileIdCheckEndTIme - fileIdCheckStartTIme);


        long fileCheckStartTIme = System.currentTimeMillis();
        // 根据文件验签 contract.pdf
        // 文件
        FileItem checkFileItem = new FileItem(new File(folderPath + "contract.pdf"));
        CommonRequest signCheckRequest = new CommonRequest();
        signCheckRequest.setApiUri("/v2/sign/check/file");// 请求Api地址
        signCheckRequest.setNeedToken(true);// 是否需要token
        signCheckRequest.setContentType(ContentType.MULTIPART);// 请求头类型
        signCheckRequest.setMethod(HttpMethod.POST);// 请求方法
        Map<String, FileItem> signCheckRequestMap = new HashMap<>(2);
        signCheckRequestMap.put("file", checkFileItem);
        signCheckRequest.setFileParams(signCheckRequestMap);
        CommonResponse signCheckV2execute = signClient.execute(signCheckRequest);
        log.info("signCheck响应状态：{}", signCheckV2execute.getResultCode());
        log.info("signCheck响应信息：{}", signCheckV2execute.getResultMessage());
        log.info("signCheck响应数据：{}", signCheckV2execute.getData());
        HashMap<String, Object> signCheckModule = (HashMap<String, Object>) signCheckV2execute.getData();
        List<HashMap<String, Object>> signCheckResults1 = (List<HashMap<String, Object>>) signCheckModule.get("sign_check_results");
        log.info("signCheckResults:{}", JSON.toJSONString(signCheckResults1));
        long fileCheckEndTIme = System.currentTimeMillis();
        log.info("接口耗时：{}ms\n\n", fileCheckEndTIme - fileCheckStartTIme);


        long endTime = System.currentTimeMillis();
        log.info("总耗时：{} ms", endTime - startTime);
    }


}
