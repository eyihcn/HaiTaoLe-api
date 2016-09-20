package com.tomtop.api.haitaole;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

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
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	private static  RestTemplate restTemplate = new RestTemplate(); 
	private  String secretKey = "5b1ffb33617016a0f5c9a2cf0d0cdcd6";
	private  String allianceCode = "9NHP5U4NL7EL03WR";
	private  String orderUrl_test = "http://58.22.126.78:6542/apis/alliances/order/standard_accept_order";//test
	private  String orderUrl = "http://www.haitaole.com/apis/alliances/order/standard_accept_order";//

	private  String shipUrl_test = "http://58.22.126.78:6542/apis/alliances/shipping/get_shipping";//test
	private  String shipUrl = "http://www.haitaole.com/apis/alliances/shipping/get_shipping";//
	

//	private static Map<String,String> resultCodeToMsg;
//	static {
//		resultCodeToMsg =  Maps.newHashMapWithExpectedSize(66);
//		
		/* order
		200	提交成功
		500	服务器错误,请过段时间推送
		300	您不是合法用户
		301	您的账户暂未开通此接口，如需开通，请联系相关业务人员申请开通
		400	请求参数填写不完整
		401	请求参数填写格式不正确
		402	请求的订单数据格式不正确
		403	自定义单号格式不符合要求
		404	省份格式不符合要求
		405	城市格式不符合要求
		406	县区格式不符合要求
		407	街道地址不符合要求
		408	订单备注格式不符合要求
		409	支付时间格式不符合要求
		410	支付方式格式不符合要求
		411	支付流水号格式不符合要求
		412	订单为空
		413	重复推送,请不要在推送
		414	省市县不能为空
		415	邮编格式错误
		416	街道地址为1-120个字
		417	省份填写不正确
		418	城市填写不正确
		419	县区填写不正确
		420	订单备注为1-255个字
		421	运费id填写错误
		422	运费金额填写错误
		423	仓储费id填写错误
		424	仓储费金额填写错误
		425	商品id值不符合格式要求
		426	商品id或sku必填一项
		427	商品sku填写错误
		428	商品sku={sku}不存在
		429	商品数量不符合格式要求
		430	商品代理单价不符合格式要求
		431	订单中的商品有重复
		432	订单商品为空
		433	收件人手机号码不能为空
		434	收件人姓名不能为空
		435	收件人手机号码格式错误
		436	收货人姓名必须为中文，2-20个字
		437	请填写正确的收件人身份证号码
		438	身份证姓名必须为中文，2-20个字
		439	订单收货身份信息为空
		440	商品id={商品id}不存在
		441	商品id={商品id }已缺货
		442	商品id={商品id }已下架
		443	商品id={商品id }数量必须大于0
		444	商品id={商品id }库存不足
		445	收货身份信息不可用
		446	商品id={商品id}数量超过了最高购买数量
		447	商品id={商品id}已下架
		448	商品id={商品id}不可搭配购买
		449	商品id={商品id}没有到{省份名称}的配送方式
		450	商品没有相同的运费
		451	运费类型为区间收费的商品总重量不可超过30kg
		452	不同库存方式的商品不可以放在同一个订单中
		453	不同行邮税率的商品不可以放在同一个订单中
		454	不同仓库的商品不可以放在同一个订单中
		455	不同商品供应商不可以放在同一个订单中
		456	商品总金额超出行邮税金额限制
		457	商品中有需要填写身份证姓名和号码
		458	这个订单需拆成多单
		459	预付金额大于网站实际金额
		460	预付金额小于网站实际金额
		461	预付金额不等于网站实际金额
*/
//	}

	
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

		String requestTime = DateUtils.currentDatetime();
		// 生成签名
		String sign = HaiTaoLeUtils.generateHaiTaiLeSign(allianceCode, requestTime, secretKey);  
		Map<String, String> reqMap = Maps.newHashMap();
		reqMap.put("request_time", requestTime);
		reqMap.put("alliance_code", allianceCode);
		reqMap.put("sign", sign);
		reqMap.put("orders_info", queryOrdersInfosXml);
		// 拼接post请求的body
		return _request(shipUrl, _generateRequestBody(reqMap));
	}
	
	private String _request(String requestUrl, String requestBody){
		System.out.println(new StringBuilder(" url: === ").append(orderUrl).append("     ;reqBody : === ").append(requestBody));

		HttpEntity httpEntity = new HttpEntity(requestBody, headers);
		
		String xmlStr = restTemplate.postForObject(requestUrl, httpEntity, String.class);
		
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
