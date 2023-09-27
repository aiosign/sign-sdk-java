package com.github.aiosign.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * AES加密工具类
 * @author Zhu Dunfeng
 * @date 2023/6/19 10:04
 */
public class AESUtils {

    /**
     * 构建aes
     * @param key 密钥
     * @return {@code AES}
     */
    private static AES buildAes(String key){
        return SecureUtil.aes(key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * AES加密
     * @param key 密钥
     * @param content 加密内容
     * @return {@code String} 密文
     */
    public static String encrypt(String key, String content){
        return buildAes(key).encryptBase64(content, StandardCharsets.UTF_8);
    }

    /**
     * AES加密
     * @param key 密钥
     * @param content 密文
     * @return {@code String} 原始内容
     */
    public static String decrypt(String key, String content){
        return buildAes(key).decryptStr(content, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String encrypt = AESUtils.encrypt("U6rmwyUKmPU7zCML","山东");
        System.out.println(encrypt);
        String s = AESUtils.decrypt("U6rmwyUKmPU7zCML", encrypt);
        System.out.println(s);
    }

}
