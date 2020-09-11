package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会签返回参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingSignResponse extends AbstractSignResponse {

    private MeetingSignModule data;

    /**
     * 模本签名参数
     */
    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class MeetingSignModule extends BaseSignObject {
        /**
         * 合同id
         */
        private String contractId;
    }

}
