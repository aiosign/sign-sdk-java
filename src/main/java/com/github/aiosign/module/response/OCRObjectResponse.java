package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * 证件识别 响应参数
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OCRObjectResponse extends AbstractSignResponse {

    private HashMap<String, Object> data;

}
