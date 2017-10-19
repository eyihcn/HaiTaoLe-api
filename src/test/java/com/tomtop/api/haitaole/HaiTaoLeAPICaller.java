package com.tomtop.api.haitaole;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;
import com.tomtop.api.haitaole.dto.HaiTaoLeOrder;
import com.tomtop.api.haitaole.dto.HaiTaoLeQueryOrderStatusInfo;
import com.tomtop.api.haitaole.dto.HaiTaoLeQueryShipOrdersInfo;
import com.tomtop.api.utils.DateUtils;
import com.tomtop.api.utils.HaiTaoLeUtils;
import com.tomtop.api.utils.JaxbUtil;
import com.tomtop.api.utils.JaxbUtil.CollectionWrapper;

public class HaiTaoLeAPICaller {

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
			beanNameToJAXBContextMapCache.put(HaiTaoLeQueryOrderStatusInfo.class.getName(),JAXBContext.newInstance(HaiTaoLeQueryOrderStatusInfo.class, CollectionWrapper.class)	);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	private static  RestTemplate restTemplate = new RestTemplate(); 
//	private  String secretKey = "5b1ffb33617016a0f5c9a2cf0d0cdcd6"; // test
//	private  String allianceCode = "9NHP5U4NL7EL03WR"; // test
	private  static final String secretKey = "da67de7c71058c47def72d718cde5b66";
	private  static final String allianceCode = "ZTWNIYFZOWSCRL27";
//	private  String orderUrl_test = "http://58.22.126.78:6542/apis/alliances/order/standard_accept_order";//test
	private  static final  String orderUrl = "http://www.haitaole.com/apis/alliances/order/standard_accept_order";//

//	private  String shipUrl_test = "http://58.22.126.78:6542/apis/alliances/shipping/get_shipping";//test
	private  static final  String shipUrl = "http://www.haitaole.com/apis/alliances/shipping/get_shipping";//
	
//	private static final String queryProductUrl_test = "http://58.22.126.78:6542/apis/alliances/product/search";//test
	private static final String queryProductUrl = "http://www.haitaole.com/apis/alliances/product/search";//

//	private static final String getOrderStatus_test = "http://58.22.126.78:6542/apis/alliances/order/getOrderStatus";
	private static final String getOrderStatus = "http://www.haitaole.com/apis/alliances/order/getOrderStatus";
	
	/**：系统订单号和自定义订单号两个必须存在一个，如两个都存在，就以系统订单号为查询条件；一次请求不能超过50个订单*/
	public String getOrderStatus(Collection<HaiTaoLeQueryOrderStatusInfo> HaiTaoLeQueryOrderStatusInfos) {
		
		if (CollectionUtils.isEmpty(HaiTaoLeQueryOrderStatusInfos) || HaiTaoLeQueryOrderStatusInfos.size() > 50) {
			throw  new IllegalArgumentException();
		}
		System.out.println("----------getOrderStatus-----------------HaiTaoLeQueryOrderStatusInfos Size---: " + HaiTaoLeQueryOrderStatusInfos.size());
		String queryOrdersStatusInfosXml =  JaxbUtil.beansToXml(beanNameToJAXBContextMapCache.get(HaiTaoLeQueryOrderStatusInfo.class.getName()), HaiTaoLeQueryOrderStatusInfos, "orders_info", null);
		Map<String, String> paramMap = _generateCommonParam();
		paramMap.put("orders_info", queryOrdersStatusInfosXml);
		
		return _request(getOrderStatus, _generateRequestBody(paramMap));
	}

	public String queryHaiTaoLeProductInfo(Set<String> skuSet){
		
		if (CollectionUtils.isEmpty(skuSet) || skuSet.size() > 100) {
			throw  new IllegalArgumentException("一次最多100个商品SKU");
		}
		System.out.println("----------queryHaiTaoLeProductInfo-----------------Sku Size---: " + skuSet.size());
		Map<String, String> paramMap = _generateCommonParam();
		paramMap.put("skus", HaiTaoLeUtils.iterableToString(skuSet));
		// 拼接post请求的body
		return _request(queryProductUrl, _generateRequestBody(paramMap));
	}
	
	public String pushOrders(HaiTaoLeOrder haiTaoLeOrder){
		
		String orderXml =  JaxbUtil.beanToXml(beanNameToJAXBContextMapCache.get(HaiTaoLeOrder.class.getName()), haiTaoLeOrder, null);

		Map<String, String> reqMap = _generateCommonParam();
		reqMap.put("order", orderXml);
		// 拼接post请求的body
		return _request(orderUrl, _generateRequestBody(reqMap));
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

		Map<String, String> reqMap = _generateCommonParam();
		reqMap.put("orders_info", queryOrdersInfosXml);
		// 拼接post请求的body
		return _request(shipUrl, _generateRequestBody(reqMap));
	}
	
	private String _request(String requestUrl, String requestBody){
		System.out.println(new StringBuilder(" url: === ").append(requestUrl).append("     ;reqBody : === ").append(requestBody));

		HttpEntity httpEntity = new HttpEntity(requestBody, headers);
		
		String xmlStr = restTemplate.postForObject(requestUrl, httpEntity, String.class);
//		System.out.println();
//		System.out.println(xmlStr);
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
	
	private Map<String, String> _generateCommonParam() {
		return generateCommonParam(allianceCode, secretKey);
	}
	
	/**
	 * 生成 api接口请求的的公共参数 request_time，alliance_code，sign
	 * @return
	 */
	public Map<String, String> generateCommonParam(String allianceCode, String secretKey) {
		String requestTime = DateUtils.currentDatetime();
		// 生成签名
		String sign = HaiTaoLeUtils.generateHaiTaoLeSign(allianceCode, requestTime, secretKey);  
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("request_time", requestTime);
		paramMap.put("alliance_code", allianceCode);
		paramMap.put("sign", sign);
		return paramMap;
	}
	
}
