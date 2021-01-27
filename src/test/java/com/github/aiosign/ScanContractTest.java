package com.github.aiosign;

import com.alibaba.fastjson.JSONObject;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.ContractAddAndFileRequest;
import com.github.aiosign.module.request.ScanContractAddRequest;
import com.github.aiosign.module.response.ContractAddAndFileResponse;
import com.github.aiosign.module.response.ScanContractAddResponse;
import com.github.aiosign.utils.QrCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * @author WeiShuai
 * @Date 2020/12/5 0005 下午 12:00
 */
@Slf4j
public class ScanContractTest {

    DefaultSignClient signClient = new DefaultSignClient("https://dzyz.gxj.guilin.gov.cn/api",
            "780460208698314752", "JEGauBWnsPAQRyihMj");

    {
        signClient.setUrlTokenKey("token");
    }

    @Test
    public void test() {

        String contractId = createContract("C:\\Users\\Administrator\\Desktop\\PaaS\\广西南宁\\低压非居民类供用电合同.pdf",
                "10781012209211428864", "测试合同" + randomInt());

//        String contractId = "18ae9a6f35d8d5f5227d4edd51eac0aa";
        String userId = "10784759142191091712";
        String sealId = "1be06f86c5964ff8a57927df4f701cec";


        ScanContractAddRequest req = new ScanContractAddRequest();
        // 合同id
        req.setContractId(contractId);
        req.setUserId(userId);
        // 签章备注
        req.setRemark("测试普通签章");
//        ScanContractAddRequest.SignParams signParam = getSignParams(sealId);
        req.setFields(Arrays.asList(getSignParams(sealId)/*, getSignParams(sealId), getSignParams(sealId)*/));
        req.setUrl("http://www.baidu.com");
        req.setQrCodeHeight(100);
        req.setQrCodeWidth(100);

        String expireTime = "2020-12-18 20:30:00";
        req.setExpireTime(expireTime);

        ScanContractAddResponse execute = signClient.execute(req);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());

        JSONObject result = new JSONObject();
        result.put("user_id", userId);
        result.put("sign_type", "common");
        result.put("prepare_id", execute.getData().getPrepareId());
        result.put("expire_time", expireTime);

        String content = result.toJSONString();
        String base64 = QrCodeUtils.getBase64(content, 200, 200);

        System.out.println("json:" + content);
        System.out.println(base64);

    }

    private ScanContractAddRequest.SignParams getSignParams(String sealId) {
        // 签章信息集合
        ScanContractAddRequest.SignParams signParam = new ScanContractAddRequest.SignParams();
        // 印章id
        signParam.setSealId(sealId);

        // 第几页
        signParam.setPageNumber(1);
        // 水平横坐标
        signParam.setHorizontal((double) randomInt());
        // 垂直纵坐标
        signParam.setVertical((double) randomInt());
        // 印章宽度
        signParam.setWidth(113.0);
        // 印章高度
        signParam.setHeight(113.0);
        return signParam;
    }

    public int randomInt() {
        int i = new Random().nextInt(500);
        return i;
    }

    /**
     * 创建合同
     *
     * @param s
     * @param s1
     */
    private String createContract(String filePath, String userId, String contractName) {
        ContractAddAndFileRequest request = new ContractAddAndFileRequest();
        request.setDescription("描述");
        FileItem fileItem = new FileItem(new File(filePath));
        request.setFileItem(fileItem);
        request.setFileName("contractName.pdf");
        request.setName(contractName);
        request.setUserId(userId);
        ContractAddAndFileResponse response = signClient.execute(request);
        log.info("响应状态：{}", response.getResultCode());
        log.info("响应信息：{}", response.getResultMessage());
        log.info("响应数据[文件id]：{}", response.getFileId());
        log.info("响应数据[合同id]：{}", response.getContractId());
        return response.getContractId();
    }
}
