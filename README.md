# 大众签章开放平台Api接口JAVA版SDK
#### 环境说明：
- 1.您需要去大众签章开放平台注册一个开发者账号，并且申请一个应用，当您的应用经过审核之后，您将获得一个appid和appsecret，这两个参数决定了您在开放平台的身份标识，只有获得了这两个参数，您才有资格调用开放平台的api接口。 
- 2.您需要凭借appid和appsecret先获取token，有些接口还需要传递签名值(后续会详细介绍),才可调用开放平台的api接口。
- 3.需要安装1.8或者以上的版本的JDK环境，maven版本最好在3.3.X以上版本编译。
- 4.sdk采用了lombok插件，所以您首先需要在您的IDE中安装lombok插件，详情可以参考[lombok插件安装参考文档][233455667665]
#### 安装依赖：
如果您使用Apache Maven来管理Java项目，只需在项目的pom.xml文件加入相应的依赖项即可。您可以在大众签章开放平台下载各版本的Maven依赖。您只需在pom.xml中声明以下依赖
````xml
<dependency>
    <groupId>com.sdgdw.sign</groupId>
    <artifactId>sign-sdk-java</artifactId>
    <version>1.0.0</version>
</dependency>
````
[233455667665]: https://projectlombok.org/setup/overview "lombok插件安装"
您也可以从github上直接拉取代码，然后运行mvn clean install命令安装到本地仓库中，如果您是直接从开放平台上下载的sdk，您也直接可以通过以下方式来引入依赖
```xml
<dependency>
    <groupId>com.sdgdw.sign</groupId>
    <artifactId>sign-sdk-java</artifactId>
    <version>1.0.0</version>
    <systemPath>您的jar所在的路径</systemPath>
</dependency>
```
如果 maven 没有从中央存储库下载 jar 包，则需要将这些依赖项添加到pom.xml文件中，否则将报告 NoClassDefFoundError 异常
```xml 
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <version>1.18.12</version>
   <optional>true</optional>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.10.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jdk8</artifactId>
    <version>2.10.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.10.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.module</groupId>
    <artifactId>jackson-module-parameter-names</artifactId>
    <version>2.10.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>net.jodah</groupId>
    <artifactId>expiringmap</artifactId>
    <version>0.5.8</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
    <optional>true</optional>
</dependency>
``` 
#### 使用说明：
以下这个代码示例向您展示了调用 Sign SDK for Java 的3个主要步骤：
- 1.创建http客户端实例并初始化。 
- 2.创建API请求对象并设置参数。 
- 3.发起请求并处理应答或异常。 
```java
package com.sdgdw.sign;


import com.sdgdw.sign.module.request.UserInfoRequest;
import com.sdgdw.sign.module.response.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
/**
 * @author yangyouwang
 * @description 个人用户测试用例
 * @since 2020/5/13
 */
@Slf4j
public class UserPersonalTest extends AbstractSignTest {
/**
* 查询用户详情信息
*/
@Test
public void userinfo() {
        //创建用户请求对象
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        // 用户id
        userInfoRequest.setUserId("您的用户id");
        //发起请求并获取响应值
        UserInfoResponse response = signClient.execute(userInfoRequest);
        log.info("响应状态：{}", response.getResultCode());
        log.info("响应信息：{}", response.getResultMessage());
        log.info("响应数据：{}", response.getData());
 }
}

```
SDK中都有相关接口的测试用例，都已经经过相关测试，您可以修改参数执行相关方法。
#### 额外说明 
1.获取token
```java 
/**
 * 获取token
*/
@Test
public void getToken(){
    TokenRequest tokenRequest = new TokenRequest("appid", "appsecret");
    TokenResponse response = signClient.execute(tokenRequest);
    if (response.isSuccess()) {
    System.out.println("获取token成功，响应值为"+response.getData());
}
}
```
SDK中接口获取token的操作都已经封装好了，获取token成功后会缓存起来，token失效后会重新获取，无需手动管理，token有效期为7200秒。

2.签名算法  
开放平台api接口的所有的post请求并且请求头为json的接口添加了签名值的校验，签名算法的机制如下：
比如你的请求json为：
```json 
{
"app_id": "710510245885661184",
"app_secret": "UJhgoFkMShBtLXcqlC",
"grant_type": "client_credentials"
}
```
首先对字符串做去空格化处理（包括\r\n），然后对字符串做HmacSHA256算法，秘钥为您的appsecret，编码一定要为UTF-8，否则可能会导致获取的加密值不同，然后转为base64编码的字符串的加密字符串，最后对该字符串做MD5摘要，最后的字符串将作为最终的签名值。
签名的工具类代码如下：
```java 
package com.sdgdw.sign.utils;

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
        return     DigestUtils.md5DigestAsHex(
      hmacSha256Sign.getBytes(StandardCharsets.UTF_8)).toUpperCase();
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

```
