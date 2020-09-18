package com.github.aiosign.module.request;

import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.BaiDuAiFaceVideoVerifyRequestResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


/**
 * 企业打款认证
 *
 * @author modificial
 * @since 2020/4/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiDuAiFaceVideoVerifyRequest extends AbstractSignRequest<BaiDuAiFaceVideoVerifyRequestResponse> {


    /**
     * 需要验证的文件
     */
    private FileItem file;

    /**
     * session id
     */
    private String sessionId;


    @Override
    public RequestInfo<BaiDuAiFaceVideoVerifyRequestResponse> getRequestInfo() {
        RequestInfo<BaiDuAiFaceVideoVerifyRequestResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.MULTIPART);
        requestInfo.setApiUri("/v1/authentication/baidu/video-verify");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(BaiDuAiFaceVideoVerifyRequestResponse.class);

        Map<String, FileItem> map = new HashMap<>(2);
        map.put("video_file", file);
        requestInfo.setFileParams(map);
        Map<String, String> params = requestInfo.getParams();
        params.put("session_id", sessionId);

        return requestInfo;
    }

}
