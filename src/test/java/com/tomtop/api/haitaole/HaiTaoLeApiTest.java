package com.tomtop.api.haitaole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.tomtop.api.utils.DateUtils;

@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
public class HaiTaoLeApiTest extends AbstractJUnit4SpringContextTests {

	final Logger log = LoggerFactory.getLogger(HaiTaoLeApiTest.class);

	@Autowired
	@Qualifier("restTemplate")
	RestTemplate restTemplate;

	@Autowired
	@Qualifier("restTemplateConnPool")
	RestTemplate restTemplateConnPool;

	@Autowired
	@Qualifier("httpClient")
	CloseableHttpClient httpClient;

	@Test
	public void testAutowiredByName() {
		log.info(this.restTemplate.getRequestFactory().getClass().toString());
		log.info(this.restTemplateConnPool.getRequestFactory().getClass().toString());
	}

	// 测试环境：http://58.22.126.78:6542/apis/alliances/order/standard_accept_order
	// 正式环境：http://www.haitaole.com/apis/alliances/order/standard_accept_order
	@Test
	public void testOrderApi() {
		String url = "http://58.22.126.78:6542/apis/alliances/order/standard_accept_order";
		Map<String,Object> reqMap = new HashMap<String, Object>();
		/*
		 * 商户代码	alliance_code	varchar(16)	是	海淘乐分配给各合作商户的唯一识别码
		 * 请求时间	request_time	datetime	是	当前请求时间（yyyy-MM-dd hh:mm:ss）
		 * 签名	sign	varchar(64)	是	根据某种组合进行加密
		 */
		String orderXml = getOrderXml();
		String alliance_code = "9NHP5U4NL7EL03WR";
		String request_time = DateUtils.currentDatetime();
		String sign = generateHaiTaiLeSign(alliance_code, request_time, secretKey); // 
		
		
		StringBuilder reqBody = new StringBuilder();
		reqBody.append("alliance_code=").append(alliance_code).append("&")
			   .append("request_time=").append(request_time).append("&")
			   .append("sign=").append(sign).append("&")
			   .append("order=").append(orderXml);
		
		Class<String> responseType = String.class;
		MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
		headers.add("Accept", "application/xml;charset=utf-8"); //
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		org.springframework.http.HttpEntity httpEntity = new org.springframework.http.HttpEntity(reqBody.toString(), headers);
		String xmlStr = restTemplate.postForObject(url, httpEntity, responseType);
		System.out.println(xmlStr);
	}

	String secretKey = "5b1ffb33617016a0f5c9a2cf0d0cdcd6";
	
	/*
	 * MD5 ( alliance_code + "|" + request_time + "|" + secret_key )
	 * 其中secret_key为海淘乐分配给各合作商户的32位的商户密钥，系统分配
	 */
	private String generateHaiTaiLeSign(String allianceCode, String requestTime, String secretKey){
		 
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
	
	@Test// 
	public void testGenerateHaiTaiLeSign() {
		String alliance_code = "9NHP5U4NL7EL03WR";
		String request_time = "2016-03-04 11:26:13";
		String secretKey = "5b1ffb33617016a0f5c9a2cf0d0cdcd6"; // 
		System.out.println("generateHaiTaiLeSign: "+generateHaiTaiLeSign(alliance_code, request_time, secretKey));
	}
	
	@Test
	public void testGetOrderXml() {
		getOrderXml();
	}
	
	private String getOrderXml() {

		BufferedReader br = null;
		String fileName = "C:\\Users\\Administrator\\Desktop\\orderXml.txt";
		StringBuilder sb = new StringBuilder();
		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(fileName));

			String line = null;
			while ((line = br.readLine()) != null) {

				// 将文本打印到控制台
//				System.out.println(line);
				
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@Test
	public void testPostCallApi() {
		String url = "http://58.22.126.78:6542/apis/alliances/order/standard_accept_order";
		Map<String,Object> reqMap = new HashMap<String, Object>();
		/*
		 * 商户代码	alliance_code	varchar(16)	是	海淘乐分配给各合作商户的唯一识别码
		 * 请求时间	request_time	datetime	是	当前请求时间（yyyy-MM-dd hh:mm:ss）
		 * 签名	sign	varchar(64)	是	根据某种组合进行加密
		 */
		String orderXml = getOrderXml();
		String alliance_code = "9NHP5U4NL7EL03WR";
		String request_time = DateUtils.currentDatetime();
		String sign = generateHaiTaiLeSign(alliance_code, request_time, secretKey); // 
		
		reqMap.put("alliance_code", alliance_code);
		reqMap.put("request_time", request_time);
		reqMap.put("sign", sign);
		reqMap.put("order", orderXml);
		
		
		try {
			postCallApi(url, reqMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void postCallApi(String urlPath, Map<String, Object> params) throws Exception{
        HttpPost httpPost = new HttpPost(urlPath);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(params.size()); 
        for (String key : params.keySet()) {
        	nameValuePairs.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")); 
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        }
    } 

}
