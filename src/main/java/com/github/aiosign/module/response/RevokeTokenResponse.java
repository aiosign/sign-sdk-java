package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
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
