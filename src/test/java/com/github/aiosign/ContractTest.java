package com.github.aiosign;

import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangyouwang
 * @description 合同Test
 * @since 2020/5/13
 */
@Slf4j
public class ContractTest extends AbstractSignTest {


    /**
     * 添加合同
     */
    @Test
    public void add() {
        ContractAddRequest contractAddRequest = new ContractAddRequest();
        // 合同名字
        contractAddRequest.setName("测试");
        // 文件id
        contractAddRequest.setFileId("0064508c31dda54bacb80fd4ebfa324b");
        // 用户id不能为空
        contractAddRequest.setUserId("00716661208384163840");
        // 描述信息
        contractAddRequest.setDescription("测试描述");
        ContractAddResponse execute = signClient.execute(contractAddRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 查询合同信息
     */
    @Test
    public void query() {
        ContractIdentityRequest contractIdentityRequest = new ContractIdentityRequest();
        // 合同id
        contractIdentityRequest.setContractId("c45e88eab763e38407621fd7e8319d23");
        ContractQueryResponse execute = signClient.execute(contractIdentityRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 查询合同信息v2
     */
    @Test
    public void queryV2() {
        ContractQueryV2Request contractQueryV2Request = new ContractQueryV2Request();
        // 合同id
        contractQueryV2Request.setContractId("c45e88eab763e38407621fd7e8319d23");
        ContractQueryV2Response execute = signClient.execute(contractQueryV2Request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 删除合同信息
     */
    @Test
    public void remove() {
        ContractIdentityListRequest contractIdentityRequest = new ContractIdentityListRequest();
        // 合同id
        contractIdentityRequest.setContractId("c45e88eab763e38407621fd7e8319d23");
        ContractDeleteResponse execute = signClient.execute(contractIdentityRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 异步渲染合同
     */
    @Test
    public void render() {
        AsyContractIdentityRequest asyContractIdentityRequest = new AsyContractIdentityRequest();
        // 合同id
        asyContractIdentityRequest.setContractId("c45e88eab763e38407621fd7e8319d23");
        ContractAddResponse execute = signClient.execute(asyContractIdentityRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    @Test
    public void bind() {
        ContractBindPhoneRequest contractBindPhoneRequest = new ContractBindPhoneRequest();
        //合同Id
        contractBindPhoneRequest.setContractId("07508a07fa13031d69c3a974f4efefff");
        //手机参数
        ContractBindPhoneRequest.BindInfo bindInfo = new ContractBindPhoneRequest.BindInfo();
        bindInfo.setPhone("15053153810");
        List<ContractBindPhoneRequest.BindInfo> bindInfoList = new ArrayList<>();
        bindInfoList.add(bindInfo);
        contractBindPhoneRequest.setParams(bindInfoList);
        ContractBindPhoneResponse execute = signClient.execute(contractBindPhoneRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    @Test
    public void querybindContract() {
        QueryBindContractRequest queryBindContractRequest = new QueryBindContractRequest();
        //合同名称
        queryBindContractRequest.setContractName("");
        //手机号
        queryBindContractRequest.setPhone("15053153810");
        QueryBindContractResponse execute = signClient.execute(queryBindContractRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    @Test
    public void ScanContractAdd() {
        ScanContractAddRequest req = new ScanContractAddRequest();
        // 合同id
        req.setContractId("c11d5a7f8b61ac5ce24d351094f868c1");
        req.setUserId("00739767865657937920");
        // 签章备注
        req.setRemark("测试普通签章");
        // 签章信息集合
        ScanContractAddRequest.SignParams signParam = new ScanContractAddRequest.SignParams();
        // 印章id
        signParam.setSealId("9268bdcb7127a7e58d5bb17be03f9d41");
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
        req.setFields(Arrays.asList(signParam));
        req.setUrl("http://www.baidu.com");
        req.setQrCodeHeight(100);
        req.setQrCodeWidth(100);

        req.setExpireTime("2020-09-18 20:30:00");

        ScanContractAddResponse execute = signClient.execute(req);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    @Test
    public void EventCertScanContractAdd() {
        EventCertScanContractAddRequest req = new EventCertScanContractAddRequest();
        // 合同id
        req.setContractId("c11d5a7f8b61ac5ce24d351094f868c1");
        req.setUserId("00739767865657937920");
        // 签章备注
        req.setRemark("测试普通签章");
        // 签章信息集合
        EventCertScanContractAddRequest.SignParams signParam = new EventCertScanContractAddRequest.SignParams();
        // 印章id
        signParam.setSealId("9268bdcb7127a7e58d5bb17be03f9d41");
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
        req.setFields(Arrays.asList(signParam));
        req.setUrl("http://www.baidu.com");
        req.setQrCodeHeight(100);
        req.setQrCodeWidth(100);

        req.setExpireTime("2020-09-18 20:30:00");

        ScanContractAddResponse execute = signClient.execute(req);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 合同废除
     */
    @Test
    public void ContractAbolish() {
        ContractAbolishRequest req = new ContractAbolishRequest();
        req.setSignId("06b0250f17974ee59051eb179f8a0b00");
        req.setUserId("00729742538705620992");
        //自定义作废参数
        ContractAbolishRequest.SignParams field=new ContractAbolishRequest.SignParams();
        //印章高度
        field.setHeight(120.0);
        //印章宽度
        field.setWidth(120.0);
        //水平横坐标
        field.setHorizontal(30.0);
        //垂直纵坐标
        field.setVertical(30.0);
        field.setPageNumber(3);
        req.setField(field);
        ContractAbolishResponse execute = signClient.execute(req);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 合同废除v2
     */
    @Test
    public void ContractAbolishV2() {
        ContractAbolishV2Request req = new ContractAbolishV2Request();
        req.setSignId("06b0250f17974ee59051eb179f8a0b00");
        req.setUserId("00729742538705620992");
        //自定义作废参数
        ContractAbolishV2Request.SignParams field=new ContractAbolishV2Request.SignParams();
        //印章高度
        field.setHeight(120.0);
        //印章宽度
        field.setWidth(120.0);
        //水平横坐标
        field.setHorizontal(30.0);
        //垂直纵坐标
        field.setVertical(30.0);
        field.setPageNumber(3);
        List<ContractAbolishV2Request.SignParams> fields=new ArrayList<>();
        fields.add(field);
        req.setFields(fields);
        ContractAbolishResponse execute = signClient.execute(req);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }


}
