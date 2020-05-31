package com.sdgdw.sign.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 签名域信息
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/9
 */
@Data
public class SignFields implements Serializable {
    /**
     * 文本域信息
     */
    private List<TextParam> textParams;

    /**
     * 签名域信息
     */
    private List<SignParam> signParams;
}
