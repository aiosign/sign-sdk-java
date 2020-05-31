package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会签返回参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingSignResponse  extends AbstractSignResponse {

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

        /**
         * 文件Id
         */
        private String fileId;

        /**
         * 签章状态
         */
        private boolean signState;

    }

}
