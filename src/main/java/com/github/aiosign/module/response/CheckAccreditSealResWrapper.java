package com.github.aiosign.module.response;

import com.github.aiosign.client.SignClient;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.utils.SealUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 核验授权信息响应信息增强
 * <p>
 * 核验授权信息接口整合获取用户印章信息接口
 *
 * @author Zhu Dunfeng
 * @date 2023/6/9 8:37
 */
public class CheckAccreditSealResWrapper {

    private final SignClient signClient;

    /**
     * 核验授权信息接口原响应信息
     */
    private final CommonResponse checkAccreditResponse;

    public CheckAccreditSealResWrapper(SignClient signClient, CommonResponse commonResponse) {
        this.signClient = signClient;
        this.checkAccreditResponse = commonResponse;
    }

    /**
     * 根据核验授权信息接口返回数据获取用户对应印章数据，并合并到核验授权信息返回中
     *
     * @return {@code CommonResponse} 合并用户印章信息后的核验授权信息响应信息
     */
    public CommonResponse wrapper() {
        if (Objects.isNull(checkAccreditResponse.getData())) {
            return checkAccreditResponse;
        }
        // 解析响应数据
        HashMap<String, Object> response = (HashMap<String, Object>) checkAccreditResponse.getData();
        // 核验授权信息接口获取到企业id，然后传入获取用户签章接口继续查看当前企业印章信息
        String companyUserId = String.valueOf(response.get("company_user_id"));
        List<HashMap<String, Object>> accreditInfo = (List<HashMap<String, Object>>) response.get("accredit_info");
        List<String> sealCodeList = accreditInfo.stream().map(v -> String.valueOf(v.get("seal_code"))).collect(Collectors.toList());
        HashMap<String, String> sealRequestBody = new HashMap<>(2);
        sealRequestBody.put("user_id", companyUserId);

        CommonRequest sealRequest = new CommonRequest();
        // 请求Api地址
        sealRequest.setApiUri("/v1/seal/getSealInfos");
        // 是否需要token
        sealRequest.setNeedToken(true);
        // 请求头类型
        sealRequest.setContentType(ContentType.JSON);
        // 请求方法
        sealRequest.setMethod(HttpMethod.POST);
        // 请求体
        sealRequest.setRequestBody(sealRequestBody);

        CommonResponse sealExecute = signClient.execute(sealRequest);
        if (Objects.isNull(sealExecute.getData())) {
            checkAccreditResponse.setReturnCode(sealExecute.getReturnCode());
            checkAccreditResponse.setReturnMessage(sealExecute.getReturnMessage());
            checkAccreditResponse.setResultCode(sealExecute.getResultCode());
            checkAccreditResponse.setResultMessage(sealExecute.getResultMessage());
            checkAccreditResponse.setData(null);
            return checkAccreditResponse;
        }

        List<HashMap<String, Object>> sealInfoModuleList = (List<HashMap<String, Object>>) sealExecute.getData();
        //<seal_code:<k,v>>
        Map<String, HashMap<String, Object>> targetSealMap = sealInfoModuleList.stream()
                .filter(map -> {
                    String sealCode = (String) map.get("seal_code");
                    return sealCodeList.contains(sealCode);
                })
                .map(map -> {
                    String size = String.valueOf(map.get("size"));
                    List<String> collect = Arrays.asList(size.trim().split("\\*"));
                    String width = collect.get(0);
                    String high = collect.get(1);
                    map.put("width", width);
                    map.put("high", high);
                    return map;
                }).collect(Collectors.toMap(k -> {
                    String sealCode = (String) k.get("seal_code");
                    return sealCode;
                }, v -> v));
        List<HashMap<String, Object>> conformityList = accreditInfo.stream()
                .map(map -> {
                    String sealCode = String.valueOf(map.get("seal_code"));
                    HashMap<String, Object> targetSealInfo = targetSealMap.get(sealCode);
                    map.put("seal_id", targetSealInfo.get("seal_id"));
                    map.put("size", targetSealInfo.get("size"));
                    map.put("width", targetSealInfo.get("width"));
                    map.put("high", targetSealInfo.get("high"));
                    if (!Objects.isNull(targetSealInfo.get("form_type"))) {
                        String formType = String.valueOf(targetSealInfo.get("form_type"));
                        map.put("form_type", formType);
                        map.put("is_consistent", SealUtils.checkSealFormType(formType));
                    }
                    return map;
                }).collect(Collectors.toList());
        response.put("accredit_info", conformityList);
        checkAccreditResponse.setData(response);
        return checkAccreditResponse;
    }
}
