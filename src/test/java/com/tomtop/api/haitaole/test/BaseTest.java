package com.tomtop.api.haitaole.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.tomtop.api.haitaole.dto.HaiTaiLeOrderShippingInfo;
import com.tomtop.api.haitaole.dto.HaiTaoLeOrder;
import com.tomtop.api.haitaole.dto.HaiTaoLeOrderItem;
import com.tomtop.api.haitaole.dto.HaiTaoLeOrderProduct;
import com.tomtop.api.haitaole.dto.HaiTaoLeQueryShipOrdersInfo;
import com.tomtop.api.haitaole.dto.HaiTaoLeShipInfoResponse;
import com.tomtop.api.utils.JaxbUtil;
import com.tomtop.api.utils.JaxbUtil.CollectionWrapper;

public class BaseTest {
	
	@Test
	public void test3()  {
		List<Integer> list = new ArrayList<Integer>();
		for (int i=0; i<10; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		int first = list.get(0);
		System.out.println("洗牌过后list中的第一个元素是： " + first);
		for (int i=1, len = list.size(); i < list.size(); i++) {
			int num = list.get(i);
			if (num != 0) {
				System.out.println(first/num);
			}else {
				System.out.println("被除数 不能为 0 ！第"+i+"个元素计算失败!");
			}
		}
	}
	
	@Test
	public void test2()  {
		int countOfZone = 0;
		String str = null;
		int start = 0;
		int end = 10000;
		for (int i=start ; i <= end ; i++) {
			str = String.valueOf(i); // 把数字转化为字符号对象
			char[] charArr = str.toCharArray();
			for (int j=0; j < charArr.length; j++) {
				if (charArr[j] == '0') {// 字符0的ascii码为48
					countOfZone ++;
				}
			}
		}
		System.out.println(start+"~"+end + " 之间 0 的有多少个  : "+countOfZone);
	}
	
	@Test
	public void testXmlToBean() throws JAXBException {
		String shippingInfoXml = readXml("C:\\Users\\Administrator\\Desktop\\HaiTaoLeShipInfoResponse.txt");
		JAXBContext jaxbContext = JAXBContext.newInstance(HaiTaoLeShipInfoResponse.class);
	
		HaiTaoLeShipInfoResponse xmlToBean = JaxbUtil.xmlToBean(shippingInfoXml, jaxbContext);
		System.out.println(xmlToBean.getMessage());
		System.out.println(xmlToBean.getShipping_info().get(0).getCustom_orders_number());
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
	
	@Test
	public void testBeansToXml() throws JAXBException {
		HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo = new HaiTaoLeQueryShipOrdersInfo("20150917125425490","20150917125425490");
		HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo1 = new HaiTaoLeQueryShipOrdersInfo("20150917125425490","20150917125425490");
		JAXBContext jaxbContext = JAXBContext.newInstance(HaiTaoLeQueryShipOrdersInfo.class, CollectionWrapper.class);
		String orderXml = JaxbUtil.beansToXml(jaxbContext,Arrays.asList(haiTaoLeQueryShipOrdersInfo,haiTaoLeQueryShipOrdersInfo1) , "orders_info", null);
		System.out.println(orderXml);
	}

	@Test
	public void test1() {

	      // 按指定模式在字符串查找
	      String line = "<?xml version= encoding=UTF-8?><response><status>200</status><message>提交成功</message><request_time>2016-03-04 11:26:13</request_time><custom_orders_number>20150917125425488</custom_orders_number><orders_info>        <order><orders_model>H1603024NBLID</orders_model><express_amount>20</express_amount>";

	      String pattern = "<orders_model>\\S+</orders_model>";

	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);

	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	    	 String statusXml = m.group();
	    	 System.out.println(statusXml);
	    	 System.out.println(statusXml.replace("<orders_model>", "").replace("</orders_model>", ""));
	      } else {
	         System.out.println("NO MATCH");
	      }

	}
	
	@Test
	public void testJaxbUtil() {
		HaiTaoLeOrder haiTaoLeOrder = new HaiTaoLeOrder();
		haiTaoLeOrder.setTitle("订单标题");
		
		HaiTaoLeOrderItem haiTaoLeOrderItem = new HaiTaoLeOrderItem();
		haiTaoLeOrder.setItem(haiTaoLeOrderItem);
		haiTaoLeOrderItem.setCustom_orders_number("20150917125425490");
		haiTaoLeOrderItem.setShipping_province("福建省");
		haiTaoLeOrderItem.setShipping_city("福州市");
		haiTaoLeOrderItem.setShipping_district("仓山区");
		haiTaoLeOrderItem.setShipping_address("xxxxxxxxx");
		haiTaoLeOrderItem.setShipping_postcode("350007");
		haiTaoLeOrderItem.setExpress_id("1");
		haiTaoLeOrderItem.setExpress_amount("20");
		haiTaoLeOrderItem.setStore_expenses_id("2");
		haiTaoLeOrderItem.setStore_amount("12");
//		haiTaoLeOrderItem.setComments("comments");
		haiTaoLeOrderItem.setAlliances_payment_date("2016-01-01 00:00:00");
		haiTaoLeOrderItem.setAlliances_payment_method("支付宝");
		haiTaoLeOrderItem.setAlliances_payment_serial("365533fleifne");
		
		HaiTaoLeOrderProduct haiTaoLeOrderProduct = new HaiTaoLeOrderProduct("1","1002001890","2","120"); 
		haiTaoLeOrderItem.setOrder_products(Arrays.asList(haiTaoLeOrderProduct));
		HaiTaiLeOrderShippingInfo haiTaiLeOrderShippingInfo = new HaiTaiLeOrderShippingInfo("18088888888","张三","320200198008177639","张三");
		haiTaoLeOrderItem.setOrder_shipping_info(Arrays.asList(haiTaiLeOrderShippingInfo));
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HaiTaoLeOrder.class);
			String orderXml = JaxbUtil.beanToXml(jaxbContext,haiTaoLeOrder, null);
			System.out.println(orderXml);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGenerateRequestBody() {
		Map<String,String> reqMap = new HashMap<String, String>();
		reqMap.put("p", "p");
		reqMap.put("p1", "p");
		reqMap.put("p2", "p");
		System.out.println(generateRequestBody(reqMap));
	}
	
	public String generateRequestBody(Map<String,String> req) {
		
//		Assert.notEmpty(req);
		
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
