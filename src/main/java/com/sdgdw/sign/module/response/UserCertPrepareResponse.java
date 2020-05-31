package com.sdgdw.sign.module.response;

import com.sdgdw.sign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 用户证书申请返回值
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCertPrepareResponse  extends AbstractSignResponse {

	private Map<String,Object> data;
}
