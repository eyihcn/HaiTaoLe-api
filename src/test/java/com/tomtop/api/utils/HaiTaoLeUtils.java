package com.tomtop.api.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class HaiTaoLeUtils {

	/**
	 * MD5 ( alliance_code + "|" + request_time + "|" + secret_key )
	 * 其中secret_key为海淘乐分配给各合作商户的32位的商户密钥，系统分配
	 */
	public static String generateHaiTaiLeSign(String allianceCode, String requestTime, String secretKey){
		 
		String plainText = allianceCode+"|"+requestTime+"|"+secretKey;
		
		StringBuilder buf = new StringBuilder();
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(plainText.getBytes());
	            byte b[] = md.digest();
	            int i;
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0){
	                	i += 256;
	                }else if(i < 16){
	                	buf.append("0");
	                }
	                buf.append(Integer.toHexString(i));
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return buf.toString();
	}
	
	
	
	/**
	 * @param resultXml 接口返回的响应结果xml
	 * @return 返回结果Code值
	 */
	public static String getResultCodeFromResultXml(String resultXml) {

	      Matcher m = Pattern.compile("<status>\\d+</status>").matcher(resultXml);
	      if ( m.find() ) {
		     Matcher m1 = Pattern.compile("\\d+").matcher(m.group());
		     if (m1.find()) {
		    	 return m1.group();
		     }
	      }
	      return "";
	}
	
	/**
	 * 
	 * @param resultXml 订单接口返回的响应结果xml
	 * @return 系统订单号
	 */
	public static String getOrdersModelFromResultXml(String resultXml) {

	      Matcher m = Pattern.compile("<orders_model>\\S+</orders_model>").matcher(resultXml);
	      if (m.find( )) {
	    	 String ordersModelXml = m.group();
	    	 if (StringUtils.isNotBlank(ordersModelXml)) {
	    		 return ordersModelXml.replace("<orders_model>", "").replace("</orders_model>", "");
	    	 }
	      } 
	      return "";
	}
}
