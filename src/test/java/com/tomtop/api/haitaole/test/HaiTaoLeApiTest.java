package com.tomtop.api.haitaole.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.tomtop.api.utils.DateUtils;
import com.tomtop.api.utils.HaiTaoLeUtils;

@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
public class HaiTaoLeApiTest extends AbstractJUnit4SpringContextTests {

	final Logger log = LoggerFactory.getLogger(HaiTaoLeApiTest.class);

	static MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
	static {
		headers.add("Accept", "application/xml;charset=utf-8"); //
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	}

	String alliance_code = "9NHP5U4NL7EL03WR";
	String secretKey = "5b1ffb33617016a0f5c9a2cf0d0cdcd6";

	String orderUrl = "http://58.22.126.78:6542/apis/alliances/order/standard_accept_order";
	String shipUrl = "http://58.22.126.78:6542/apis/alliances/shipping/get_shipping";

	@Autowired
	@Qualifier("restTemplate")
	RestTemplate restTemplate;

	@Autowired
	@Qualifier("restTemplateConnPool")
	RestTemplate restTemplateConnPool;

	@Test
	public void testAutowiredByName() {
		log.info(this.restTemplate.getRequestFactory().getClass().toString());
		log.info(this.restTemplateConnPool.getRequestFactory().getClass().toString());
	}

	// 正式环境：http://www.haitaole.com/apis/alliances/shipping/get_shipping
	@Test
	public void testShipApi_restTemplate() {
		
		String orders_info_Xml = readXml("C:\\Users\\Administrator\\Desktop\\orders_info.txt");
		String alliance_code = "9NHP5U4NL7EL03WR"; // test
		String request_time = DateUtils.currentDatetime();
		String sign = HaiTaoLeUtils.generateHaiTaoLeSign(alliance_code, request_time, secretKey); //
		
		Map<String, String> req = new HashMap<String, String>();
		req.put("orders_info", orders_info_Xml);
		req.put("alliance_code", alliance_code);
		req.put("request_time", request_time);
		req.put("sign", sign);
		HttpEntity httpEntity = new HttpEntity(generateRequestBody(req), headers);
		String xmlStr = restTemplate.postForObject(shipUrl, httpEntity, String.class);
	}
	
	@Test
	public void testShipApi_restTemplateConnPool() {
		
		String orders_info_Xml = readXml("C:\\Users\\Administrator\\Desktop\\orders_info.txt");
		String alliance_code = "9NHP5U4NL7EL03WR";
		String request_time = DateUtils.currentDatetime();
		String sign = HaiTaoLeUtils.generateHaiTaoLeSign(alliance_code, request_time, secretKey); //
		
		Map<String, String> req = new HashMap<String, String>();
		req.put("orders_info", orders_info_Xml);
		req.put("alliance_code", alliance_code);
		req.put("request_time", request_time);
		req.put("sign", sign);
		HttpEntity httpEntity = new HttpEntity(generateRequestBody(req), headers);
		String xmlStr = restTemplateConnPool.postForObject(shipUrl, httpEntity, String.class);
	}
	
	// 正式环境：http://www.haitaole.com/apis/alliances/order/standard_accept_order
	@Test
	public void testOrderApi() {
		/*
		 * 商户代码 alliance_code varchar(16) 是 海淘乐分配给各合作商户的唯一识别码 请求时间 request_time
		 * datetime 是 当前请求时间（yyyy-MM-dd hh:mm:ss） 签名 sign varchar(64) 是
		 * 根据某种组合进行加密
		 */
		String orderXml = readXml("C:\\Users\\Administrator\\Desktop\\orderXml.txt");
		String alliance_code = "9NHP5U4NL7EL03WR";
		String request_time = DateUtils.currentDatetime();
		String sign = HaiTaoLeUtils.generateHaiTaoLeSign(alliance_code, request_time, secretKey); //

		StringBuilder reqBody = new StringBuilder();
		reqBody.append("alliance_code=").append(alliance_code).append("&").append("request_time=").append(request_time)
				.append("&").append("sign=").append(sign).append("&").append("order=").append(orderXml);
		HttpEntity httpEntity = new HttpEntity(reqBody.toString(), headers);
		log.info(httpEntity.toString());
		String xmlStr = restTemplate.postForObject(orderUrl, httpEntity, String.class);
		log.info(xmlStr);
	}

	private String readXml(String filePath) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = br.readLine()) != null) {
				// 将文本打印到控制台
				System.out.println(line);
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public String generateRequestBody(Map<String,String> req) {
		
		Assert.notEmpty(req);
		
		StringBuilder reqBody = new StringBuilder();
		for(Entry<String, String> entry : req.entrySet()) {
			reqBody.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if (reqBody.length() == 0) {
			return null;
		}
		return reqBody.substring(0, reqBody.length()-1);
		
	}

}
