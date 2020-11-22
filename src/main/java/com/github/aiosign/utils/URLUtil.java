package com.github.aiosign.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLUtil {
 
	/**
	 * 在url上往后追加参数
	 * @Description:    
	 * @param url
	 * @param data
	 * @return
	 * EHRvendor4 <Email>xiong.bw@neusoft.com</Email> 
	 * 2020年8月12日
	 */
	public static String appendUrl(String url, Map<String, Object> data) {
	    String newUrl = url;
	    StringBuffer param = new StringBuffer();
	    for (String key : data.keySet()) {
	        param.append(key + "=" + data.get(key).toString() + "&");
	    }
	    String paramStr = param.toString();
	    paramStr = paramStr.substring(0, paramStr.length() - 1);
	    if (newUrl.indexOf("?") >= 0) {
	        newUrl += "&" + paramStr;
	    } else {
	        newUrl += "?" + paramStr;
	    }
	    return newUrl;
	}
 
	/**
	 * 取出指定的参数
	 * @Description:    
	 * @param url
	 * @param name
	 * @return
	 * EHRvendor4 <Email>xiong.bw@neusoft.com</Email> 
	 * 2020年8月12日
	 */
	public static String getParamByUrl(String url, String param) {
	    url += "&";
	    String pattern = "(\\?|&){1}#{0,1}" + param + "=[a-z|A-Z|0-9|\\-|\\:]*(&{1})";
 
	    Pattern r = Pattern.compile(pattern);
 
	    Matcher m = r.matcher(url);
	    if (m.find()) {
	        return m.group(0).split("=")[1].replace("&", "");
	    } else {
	        return null;
	    }
	}
	
//	public static void main(String[] args) throws Exception {
//	     String url = "http://test.com?name=abd&id=1&age=18";
//	    String url = "/sc/dc/addInfoOfNewEmp/addInfoOfNewEmpVM/biztemplateview.view?employeeId=2cdf3bd220b44930b7aa068631f723ee&eventCode=addInfoOfNewEmp&procInstId=6c8c40f:173bdb3d2e5:-7cf4";
//	    System.out.println(getParamByUrl(url, "procInstId"));
//	    Map<String, Object> data = new HashMap<>();
//	    data.put("123", "12332123132213");
//	    System.out.println(appendUrl(url, data));
//	}
}