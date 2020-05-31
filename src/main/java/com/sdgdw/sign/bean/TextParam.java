package com.sdgdw.sign.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 文本域信息
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/9
 */
@Data
public class TextParam implements Serializable {
    /**
     * 签章域key
     */
    private String key;

    /**
     * 签章域value
     */
    private String value;
}
