package com.tomtop.api.haitaole;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;
import com.tomtop.api.haitaole.dto.HaiTaoLeOrder;
import com.tomtop.api.haitaole.dto.HaiTaoLeQueryShipOrdersInfo;
import com.tomtop.api.utils.DateUtils;
import com.tomtop.api.utils.HaiTaoLeUtils;
import com.tomtop.api.utils.JaxbUtil;
import com.tomtop.api.utils.JaxbUtil.CollectionWrapper;

public class HaiTaoLeAPICallerByConnPool {
	
	final Logger log = LoggerFactory.getLogger(HaiTaoLeAPICallerByConnPool.class);

	private static MultiValueMap<String, Object> headers;
	private static Map<String,JAXBContext> beanNameToJAXBContextMapCache;
	static {
		headers = new LinkedMultiValueMap<String, Object>();
		headers.add("Accept", "application/xml;charset=utf-8");
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		//===========================================
		beanNameToJAXBContextMapCache = Maps.newHashMap();
		try {
			beanNameToJAXBContextMapCache.put(HaiTaoLeOrder.class.getName(), JAXBContext.newInstance(HaiTaoLeOrder.class));
			beanNameToJAXBContextMapCache.put(HaiTaoLeQueryShipOrdersInfo.class.getName(),JAXBContext.newInstance(HaiTaoLeQueryShipOrdersInfo.class, CollectionWrapper.class)	);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Autowired
	@Qualifier("restTemplateConnPool")
	private  RestTemplate restTemplate;
	
	private  String secretKey = "5b1ffb33617016a0f5c9a2cf0d0cdcd6";
	private  String allianceCode = "9NHP5U4NL7EL03WR";
	private  String orderUrl_test = "http://58.22.126.78:6542/apis/alliances/order/standard_accept_order";//test
	private  String orderUrl = "http://www.haitaole.com/apis/alliances/order/standard_accept_order";//

	private  String shipUrl_test = "http://58.22.126.78:6542/apis/alliances/shipping/get_shipping";//test
	private  String shipUrl = "http://www.haitaole.com/apis/alliances/shipping/get_shipping";//
	
	
	public String pushOrders(HaiTaoLeOrder haiTaoLeOrder){
		
		String orderXml =  JaxbUtil.beanToXml(beanNameToJAXBContextMapCache.get(HaiTaoLeOrder.class.getName()), haiTaoLeOrder, null);

		String requestTime = DateUtils.currentDatetime();
		// 生成签名
		String sign = HaiTaoLeUtils.generateHaiTaiLeSign(allianceCode, requestTime, secretKey);  
		Map<String, String> reqMap = Maps.newHashMap();
		reqMap.put("request_time", requestTime);
		reqMap.put("alliance_code", allianceCode);
		reqMap.put("sign", sign);
		reqMap.put("order", orderXml);
		// 拼接post请求的body
		return _request(orderUrl_test, _generateRequestBody(reqMap));
	}
	
	/**
	 * 
	 * @param haiTaoLeQueryShipOrdersInfos 
	 * 					系统订单号和自定义订单号两个必须存在一个，如两个都存在，就以系统订单号为查询条件；一次请求不能超过50个订单。
	 * @return
	 */
	public String queryShip(Collection<HaiTaoLeQueryShipOrdersInfo> haiTaoLeQueryShipOrdersInfos ){
		
		if (CollectionUtils.isEmpty(haiTaoLeQueryShipOrdersInfos) || haiTaoLeQueryShipOrdersInfos.size() > 50) {
			throw  new IllegalArgumentException();
		}
	
		String queryOrdersInfosXml =  JaxbUtil.beansToXml(beanNameToJAXBContextMapCache.get(HaiTaoLeQueryShipOrdersInfo.class.getName()), haiTaoLeQueryShipOrdersInfos, "orders_info", null);

		String requestTime = DateUtils.currentDatetime();
		// 生成签名
		String sign = HaiTaoLeUtils.generateHaiTaiLeSign(allianceCode, requestTime, secretKey);  
		Map<String, String> reqMap = Maps.newHashMap();
		reqMap.put("request_time", requestTime);
		reqMap.put("alliance_code", allianceCode);
		reqMap.put("sign", sign);
		reqMap.put("orders_info", queryOrdersInfosXml);
		// 拼接post请求的body
		return _request(shipUrl_test, _generateRequestBody(reqMap));
	}
	
	private String _request(String requestUrl, String requestBody){
		
		log.info(new StringBuilder(" requestUrl: === ").append(requestUrl).append(" ;reqBody : === ").append(requestBody).toString());
		HttpEntity httpEntity = new HttpEntity(requestBody, headers);
		String xmlStr = restTemplate.postForObject(requestUrl, httpEntity, String.class);
		log.info(xmlStr);
		return xmlStr;
	}
	
	private String _generateRequestBody(Map<String,String> req) {
		
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
