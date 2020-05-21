package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回收token返回值
 * @author modificial
 * @description
 * @since 2020/5/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RevokeTokenResponse extends AbstractSignResponse {
    /**
     * 操作是否成功
     */
    private Boolean data;
}
