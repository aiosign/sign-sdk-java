package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Map;

/**
 * 用户证书续期返回值
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CertRenewalResponse extends AbstractSignResponse {

    private Map<String, Object> data;

}
