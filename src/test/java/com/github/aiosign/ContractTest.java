package com.github.aiosign;

import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
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
}
