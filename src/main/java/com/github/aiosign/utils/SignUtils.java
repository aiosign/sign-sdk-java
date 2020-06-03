package com.github.aiosign.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 签名算法工具类
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/3/26
 */
@Slf4j
public class SignUtils {

    /**
     * 签名算法 先将字符串做HmacSHA256签名，密钥key为appsecret，然后将字符串做MD5摘要，最后全部转为大写，得到最终签名值
     *
     * @param json    the json string
     * @param signKey the sign key
     * @return the string
     */
    public static String createSign(String json, String signKey) {
        Assert.hasText(json, "待签名字符串不能为空");
        Assert.hasText(signKey, "签名秘钥不能为空");
        Assert.hasText(json, "签名类型不能为空");
        json = StringUtils.trimAllWhitespace(json);
        String hmacSha256Sign = createHmacSha256Sign(json, signKey);
        if (StringUtils.isEmpty(hmacSha256Sign)) {
            throw new IllegalStateException("生成签名失败");
        }
        return DigestUtils.md5DigestAsHex(hmacSha256Sign.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    /**
     * 校验签名是否正确.
     *
     * @param params      需要校验的参数 string
     * @param signKey     校验的签名Key appsecret
     * @param currentSign a {@link java.lang.String} object.
     * @return true - 签名校验成功，false - 签名校验失败
     */
    public static boolean checkSign(String params, String signKey, String currentSign) {
        String sign = createSign(params, signKey);
        return sign.equalsIgnoreCase(currentSign);
    }

    /**
     * HmacSHA256签名
     *
     * @param message a {@link java.lang.String} object.
     * @param key a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String createHmacSha256Sign(String message, String key) {
        try {
            Mac sha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256.init(secretKeySpec);
            byte[] bytes = sha256.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException var5) {
            log.error(var5.getMessage(), var5);
            return null;
        }
    }
}
