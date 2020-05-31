package com.sdgdw.sign.utils;

import com.sdgdw.sign.base.FileItem;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.net.Proxy.Type;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 网络工具类。
 *
 * @author modificial
 * @version $Id: $Id
 */
@Slf4j
public class WebUtils {

    private static final String DEFAULT_CHARSET = StandardCharsets.UTF_8.name();
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";

    private static SSLContext ctx = null;

    private static HostnameVerifier verifier;

    private static SSLSocketFactory socketFactory = null;

    private static int keepAliveTimeout = 0;

    /**
     * 是否校验SSL服务端证书，默认为需要校验
     */
    private static volatile boolean serverTrusted = true;

    static {

        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()},
                    new SecureRandom());

            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);

            socketFactory = ctx.getSocketFactory();
        } catch (Exception e) {
            //ignore
        }
        verifier = (hostname, session) -> {
            return false; //不允许URL的主机名和服务器的标识主机名不匹配的情况
        };

    }

    private WebUtils() {
    }

    /**
     * 设置是否校验SSL服务端证书
     *
     * @param needCheckServerTrusted true：需要校验（默认，推荐）；
     *                               <p>
     *                               false：不需要校验（仅当部署环境不便于进行服务端证书校验，且已有其他方式确保通信安全时，可以关闭SSL服务端证书校验功能）
     */
    public static void setNeedCheckServerTrusted(boolean needCheckServerTrusted) {
        serverTrusted = needCheckServerTrusted;
    }

    /**
     * 设置KeepAlive连接超时时间，一次HTTP请求完成后，底层TCP连接将尝试尽量保持该超时时间后才关闭，以便其他HTTP请求复用TCP连接
     * <p>
     * KeepAlive连接超时时间设置为0，表示使用默认的KeepAlive连接缓存时长（目前为5s）
     * <p>
     * 连接并非一定能保持指定的KeepAlive超时时长，比如服务端断开了连接
     * <p>
     * 注：该方法目前只在JDK8上测试有效
     *
     * @param timeout KeepAlive超时时间，单位秒
     */
    public static void setKeepAliveTimeout(int timeout) {
        if (timeout < 0 || timeout > 60) {
            throw new RuntimeException("keep-alive timeout value must be between 0 and 60.");
        }
        keepAliveTimeout = timeout;
    }

    /**
     * 执行HTTP POST请求，可使用代理proxy。
     *
     * @param url            请求地址
     * @param params         请求参数
     * @param charset        字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @param proxyHost      代理host，传null表示不使用代理
     * @param proxyPort      代理端口，传0表示不使用代理
     * @return 响应字符串
     * @throws java.io.IOException
     */
    public static String doPost(String url, Map<String, String> params, String charset,
                                int connectTimeout, int readTimeout, String proxyHost,
                                int proxyPort) throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;
        String query = buildQuery(params, charset);
        byte[] content = {};
        if (query != null) {
            content = query.getBytes(charset);
        }
        return doPost(url, ctype, content, connectTimeout, readTimeout, proxyHost, proxyPort,null);
    }

    /**
     * 执行HTTP POST请求。
     *
     * @param url            请求地址
     * @param ctype          请求类型
     * @param content        请求字节数组
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @param proxyHost      代理host，传null表示不使用代理
     * @param proxyPort      代理端口，传0表示不使用代理
     * @return 响应字符串
     * @param sign a {@link java.lang.String} object.
     */
    public static String doPost(String url, String ctype, byte[] content, int connectTimeout,
                                int readTimeout, String proxyHost, int proxyPort,String sign) {
        Assert.isTrue(StringUtils.hasLength(url) && (url.startsWith("http://") || url.startsWith("https://")), "url地址不合法");
        Assert.hasText(ctype, "请求头类型不能为空");
        Assert.notNull(content, "请求体数据不能为空");
        Assert.isTrue(connectTimeout > 0, "超时时间必须大于0");
        Assert.isTrue(readTimeout > 0, "读取时间必须大于0");
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            conn = null;
            if (!StringUtils.isEmpty(proxyHost)) {
                conn = getConnection(new URL(url), METHOD_POST, ctype, proxyHost, proxyPort,sign);
            } else {
                conn = getConnection(new URL(url), METHOD_POST, ctype,sign);
            }
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();

            }
        }

        return rsp;
    }

    /**
     * post请求，请求头为application/json,不使用代理
     *
     * @param url            a {@link java.lang.String} object.
     * @param content        a {@link java.lang.String} object.
     * @param connectTimeout a int.
     * @param readTimeout    a int.
     * @param proxyHost      a {@link java.lang.String} object.
     * @param proxyPort      a {@link java.lang.Integer} object.
     * @param sign           a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static String doPostJson(String url, String content, int connectTimeout, int readTimeout,String proxyHost,Integer proxyPort,String sign) {
        return doPost(url, "application/json", content.getBytes(StandardCharsets.UTF_8),
                connectTimeout, readTimeout,proxyHost,proxyPort,sign);
    }

    /**
     * 执行带文件上传的HTTP POST请求。
     *
     * @param url            请求地址
     * @param params         文本请求参数
     * @param fileParams     文件请求参数
     * @param charset        字符集，如UTF-8, GBK, GB2312
     * @param connectTimeout 连接超时时间
     * @param readTimeout    请求超时时间
     * @param proxyHost      代理host，传null表示不使用代理
     * @param proxyPort      代理端口，传0表示不使用代理
     * @return 响应字符串
     * @throws java.io.IOException
     */
    public static String doPost(String url, Map<String, String> params,
                                Map<String, FileItem> fileParams, String charset,
                                int connectTimeout, int readTimeout, String proxyHost,
                                int proxyPort) throws IOException {
        if (fileParams == null || fileParams.isEmpty()) {
            return doPost(url, params, charset, connectTimeout, readTimeout, proxyHost, proxyPort);
        }

        String boundary = System.currentTimeMillis() + ""; // 随机分隔线
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                String ctype = "multipart/form-data;boundary=" + boundary + ";charset=" + charset;
                conn = null;
                if (!StringUtils.isEmpty(proxyHost)) {
                    conn = getConnection(new URL(url), METHOD_POST, ctype, proxyHost, proxyPort,null);
                } else {
                    conn = getConnection(new URL(url), METHOD_POST, ctype,null);
                }
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }

            try {
                out = conn.getOutputStream();

                byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);

                // 组装文本请求参数
                Set<Entry<String, String>> textEntrySet = params.entrySet();
                for (Entry<String, String> textEntry : textEntrySet) {
                    byte[] textBytes = getTextEntry(textEntry.getKey(), textEntry.getValue(),
                            charset);
                    out.write(entryBoundaryBytes);
                    out.write(textBytes);
                }

                // 组装文件请求参数
                Set<Entry<String, FileItem>> fileEntrySet = fileParams.entrySet();
                for (Entry<String, FileItem> fileEntry : fileEntrySet) {
                    FileItem fileItem = fileEntry.getValue();
                    byte[] fileBytes = getFileEntry(fileEntry.getKey(), fileItem.getFileName(),
                            fileItem.getMimeType(), charset);
                    out.write(entryBoundaryBytes);
                    out.write(fileBytes);
                    out.write(fileItem.getContent());
                }

                // 添加请求结束标志
                byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
                out.write(endBoundaryBytes);
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static byte[] getTextEntry(String fieldName, String fieldValue,
                                       String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    private static byte[] getFileEntry(String fieldName, String fileName, String mimeType,
                                       String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(fileName);
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 响应字符串
     */
    public static String doGet(String url, Map<String, String> params) {
        try {
            return doGet(url, params, DEFAULT_CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行HTTP GET请求。
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 字符集，如UTF-8, GBK, GB2312
     * @return 响应字符串
     * @throws java.io.IOException
     */
    public static String doGet(String url, Map<String, String> params, String charset) throws IOException {
        HttpURLConnection conn = null;
        String rsp;
        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            String query = buildQuery(params, charset);
            try {
                conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype,null);
            } catch (IOException e) {
                throw e;
            }

            try {
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    /**
     * <p>getConnection.</p>
     *
     * @param url    a {@link java.net.URL} object.
     * @param method a {@link java.lang.String} object.
     * @param ctype  a {@link java.lang.String} object.
     * @param sign   a {@link java.lang.String} object.
     * @return a {@link java.net.HttpURLConnection} object.
     */
    public static HttpURLConnection getConnection(URL url, String method, String ctype, String sign) {
        try {
            return getConnection(url, method, ctype, null, sign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>getConnection.</p>
     *
     * @param url       a {@link java.net.URL} object.
     * @param method    a {@link java.lang.String} object.
     * @param ctype     a {@link java.lang.String} object.
     * @param proxyHost a {@link java.lang.String} object.
     * @param proxyPort a int.
     * @param sign      a {@link java.lang.String} object.
     * @return a {@link java.net.HttpURLConnection} object.
     */
    public static HttpURLConnection getConnection(URL url, String method, String ctype,
                                                  String proxyHost, int proxyPort, String sign) {
        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        try {
            return getConnection(url, method, ctype, proxy, sign);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype, Proxy proxy, String sign) throws IOException {
        HttpURLConnection conn;
        if ("https".equals(url.getProtocol())) {
            HttpsURLConnection connHttps;
            if (proxy != null) {
                connHttps = (HttpsURLConnection) url.openConnection(proxy);
            } else {
                connHttps = (HttpsURLConnection) url.openConnection();
            }
            if (!serverTrusted) {
                //设置不校验服务端证书的SSLContext
                connHttps.setSSLSocketFactory(socketFactory);
                connHttps.setHostnameVerifier(verifier);
            }
            conn = connHttps;
        } else {
            if (proxy != null) {
                conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                conn = (HttpURLConnection) url.openConnection();
            }
        }
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json,text/plain,text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent", "sign-sdk-java");
        conn.setRequestProperty("Content-Type", ctype);
        if(StringUtils.hasText(sign)) {
            conn.setRequestProperty("sign", sign);
        }
        return conn;
    }

    private static URL buildGetUrl(String strUrl, String query) throws IOException {
        URL url = new URL(strUrl);
        if (StringUtils.isEmpty(query)) {
            return url;
        }

        if (StringUtils.isEmpty(url.getQuery())) {
            if (strUrl.endsWith("?")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "?" + query;
            }
        } else {
            if (strUrl.endsWith("&")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "&" + query;
            }
        }

        return new URL(strUrl);
    }

    /**
     * <p>buildQuery.</p>
     *
     * @param params  a {@link java.util.Map} object.
     * @param charset a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.areNotEmpty(name, value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }

                query.append(name).append("=").append(URLEncoder.encode(value, charset));
            }
        }

        return query.toString();
    }

    /**
     * <p>getResponseAsString.</p>
     *
     * @param conn a {@link java.net.HttpURLConnection} object.
     * @return a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        //此时设置KeepAlive超时所需数据结构才刚初始化完整，可以通过反射修改
        //同时也不宜将修改时机再滞后，因为可能后续连接缓存类已经消费了默认的KeepAliveTimeout值，再修改已经无效
        setKeepAliveTimeout(conn);
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                String errorMsg=conn.getResponseCode() + ":" + conn.getResponseMessage();
                log.error("调用api发生错误，错误信息为{}",errorMsg);
                return null;
            } else {
                log.error("调用api发生错误，错误信息为{}",msg);
                return null;
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    /**
     * 使用默认的UTF-8字符集反编码请求参数值。
     *
     * @param value 参数值
     * @return 反编码后的参数值
     */
    public static String decode(String value) {
        return decode(value, DEFAULT_CHARSET);
    }

    /**
     * 使用默认的UTF-8字符集编码请求参数值。
     *
     * @param value 参数值
     * @return 编码后的参数值
     */
    public static String encode(String value) {
        return encode(value, DEFAULT_CHARSET);
    }

    /**
     * 使用指定的字符集反编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 反编码后的参数值
     */
    public static String decode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLDecoder.decode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * 使用指定的字符集编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 编码后的参数值
     */
    public static String encode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLEncoder.encode(value, charset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private static Map<String, String> getParamsFromUrl(String url) {
        Map<String, String> map = null;
        if (url != null && url.indexOf('?') != -1) {
            map = splitUrlQuery(url.substring(url.indexOf('?') + 1));
        }
        if (map == null) {
            map = new HashMap<>();
        }
        return map;
    }

    /**
     * 从URL中提取所有的参数。
     *
     * @param query URL地址
     * @return 参数映射
     */
    public static Map<String, String> splitUrlQuery(String query) {
        Map<String, String> result = new HashMap<String, String>();

        String[] pairs = query.split("&");
        if (pairs.length > 0) {
            for (String pair : pairs) {
                String[] param = pair.split("=", 2);
                if (param.length == 2) {
                    result.put(param[0], param[1]);
                }
            }
        }

        return result;
    }

    /**
     * 由于HttpUrlConnection不支持设置KeepAlive超时时间，该方法通过反射机制设置
     *
     * @param connection 需要设置KeepAlive的连接
     */
    private static void setKeepAliveTimeout(HttpURLConnection connection) {
        if (keepAliveTimeout == 0) {
            return;
        }
        try {

            Field delegateHttpsUrlConnectionField =
                    Class.forName("sun.net.www.protocol.https.HttpsURLConnectionImpl").getDeclaredField(
                            "delegate");
            delegateHttpsUrlConnectionField.setAccessible(true);
            Object delegateHttpsUrlConnection = delegateHttpsUrlConnectionField.get(connection);

            Field httpClientField = Class.forName("sun.net.www.protocol.http.HttpURLConnection").getDeclaredField(
                    "http");
            httpClientField.setAccessible(true);
            Object httpClient = httpClientField.get(delegateHttpsUrlConnection);

            Field keepAliveTimeoutField = Class.forName("sun.net.www.http.HttpClient").getDeclaredField(
                    "keepAliveTimeout");
            keepAliveTimeoutField.setAccessible(true);
            keepAliveTimeoutField.setInt(httpClient, keepAliveTimeout);
        } catch (Throwable ignored) {
            //设置KeepAlive超时只是一种优化辅助手段，设置失败不应阻塞主链路，设置失败不应影响功能
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain,
                                       String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain,
                                       String authType) {
        }
    }
}
