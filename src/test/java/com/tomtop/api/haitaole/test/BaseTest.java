package com.tomtop.api.haitaole.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import com.tomtop.api.utils.DateUtils;
import com.tomtop.api.utils.JaxbUtil;
import com.tomtop.api.utils.JaxbUtil.CollectionWrapper;

public class BaseTest {


	@Test
	public void testDownLodeImgs() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("3181836", "A&A");
		System.out.println(map.get("1"));
		System.out.println(map);
	}

	@Test
	public void testDownLodeImgsUnit() throws MalformedURLException {
		String imgURL = "https://img.alicdn.com/bao/uploaded/i4/TB1.BpXOFXXXXbzXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg";
		String imgname = "aaaaaaaaaa.jpg";
		String localAdd = "D:\\imgs\\";
		try {
			downloadPhotos(imgURL, localAdd, imgname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传递图片的url，将图片下载在对应的文件中
	 * 
	 * @param urlString
	 *            图片连接
	 * @param localAdd
	 *            下载到硬盘位置
	 * @param imgname
	 *            下载的图片重命名名字
	 * @author xvk
	 * @throws Exception
	 */

	public static void downloadPhotos(String urlString, String localAdd, String imgname) throws Exception {
		File dir = new File(localAdd);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			File file = new File(localAdd + "/" + imgname);
			OutputStream os = new FileOutputStream(file);
			// 创建一个url对象
			URL url = new URL(urlString);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				// 写入文件
				os.write(temp);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// http://merchant.wish.com/transaction/55ef86b75748497074a67423

	@Test
	public void test13() throws Exception {
		String imgURL = "https://s3-us-west-1.amazonaws.com/sweeper-sandbox-productimage/df01e68e56976ca6b937f4bb381b090b.jpg";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			// 创建一个url对象
			URL url = new URL(imgURL);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				// 写入文件
				os.write(temp);
			}
			String strImg = Base64.getEncoder().encodeToString(os.toByteArray());

			System.out.println(strImg.hashCode());
			System.out.println(strImg.length());
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test12() throws Exception {
		System.out.println("adsf~ daf".split("~")[0]);
	}

	@Test
	public void test11() throws Exception {
		String regEx = "^[0-9a-zA-Z,\\/'\\-&\\.\\s]*$";
		// 编译正则表达式
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z,\\/'\\-&\\.\\s]*$");
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("dsfad");
		System.out.println(matcher.matches());
		System.out.println(pattern.matcher("dsDf/'-&. ad").matches());
		System.out.println(pattern.matcher("dsf/'-&. 09ad").matches());
		System.out.println(pattern.matcher("dDsf/'-&. 09?ad").matches());
		System.out.println(pattern.matcher("dsf/'5%#@-&. 09?ad").matches());
	}

	@Test
	public void test10() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 6; i++) {
			executor.execute(new Runnable() {
				public void run() {
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " ....end");
				}
			});
		}
		executor.shutdown();
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Test
	public void test9() {
		String str = "http://merchant.wish.com/transaction/55ef86b75748497074a67423";
		System.out.println(
				str.substring(str.lastIndexOf("/") + 1));
	}
	
	@Test
	public void test8() {
		double init = 10;
		int count = 1;
		double percentPerDay = 1.01F;// 平常平均每天一个点
		double handlePercent = 0.2F; // 平常
		double bigPercentPerDay = 1.02F;
		// double bigPercentPerDay = 1.04F;
		// double handleBigPercent = 0.5F; // 大行情半仓
		double handleBigPercent = 1F; // 大行情全仓
		Date currDate = new Date();
		System.out.println(DateUtils.formatDate(currDate));
		int days = 365 * 3;
		for (int i = 0; i < days; i++) {
			// 周末直接跳过
			if (DateUtils.isWeekendDay(currDate)) {
				currDate = DateUtils.incrementDate(currDate);
				continue;
			}
			// 每七天有一次大行情
			if (count == 7) {
				init = init * handleBigPercent * percentPerDay + (init * (1 - handleBigPercent));
				count = 1;
			} else {
				init = init * handlePercent * bigPercentPerDay + (init * (1 - handlePercent));
				count++;
			}
			currDate = DateUtils.incrementDate(currDate);
		}
		System.out.println(DateUtils.formatDate(currDate));
		System.out.println(init);

	}

	@Test
	public void test7() {
		int min = 9;
		int max = 10;
		Random random = new Random();
		System.out.println(random.nextFloat());
		double d = min + ((max - min) * new Random().nextDouble());
		System.out.println(d);
		// RoundingMode.DOWN 不需要四舍五入
		BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.DOWN);
		System.out.println(bg.floatValue());
		System.out.println(
				"Kkmoon 4ch Channel Full Ahd 1080n/720p 700tvl Cctv Surveillance Dvr Security System Hdmi P2p Cloud Onvif Network Digital Video Recorder + Can Choose 1tb Seagate Hard Drive + 4*outdoor/indoor Infrared Bullet Camera + 4*60ft Cable Support Ir-cut Filter Infrared Night Vision Weatherproof Plug And Play"
						.length());

	}

	@Test
	public void test6() {
		System.out.println(parseMultiSku("DV7^10,DV8^20,DV9,DV8^2"));
		System.out.println(parseMultiSku("DV7^10"));
	}

	/**
	 * 分解sku串，例如 "DV7^10,DV8^20,DV9", 分解为哈希 {"DV7":10, "DV8":20, "DV9":1},
	 * 同时支持单个sku
	 *
	 * @param skuStr:
	 *            sku串: DV7^10,DV8^20,DV9
	 * @return Map<String, Integer>: {"DV7":10, "DV8":20, "DV9":1}
	 */
	public static Map<String, Integer> parseMultiSku(String skuStr) {

		Map<String, Integer> multiSkus = new LinkedHashMap<String, Integer>();

		String[] skuMultiQtys = skuStr.split(",");

		for (String skuMultiQty : skuMultiQtys) {
			String[] skuQty = skuMultiQty.split("\\^");
			String sku = skuQty[0].trim();
			int qty = 1;
			if (skuQty.length == 2) {
				String qtyStr = skuQty[1];
				if (!qtyStr.matches("\\d+")) {
					qty = 0;
				} else {
					qty = Integer.valueOf(qtyStr);
				}
			}
			Integer lodQty = multiSkus.get(sku);
			if (null != lodQty) {
				multiSkus.put(sku, lodQty + qty);
			} else {
				multiSkus.put(sku, qty);
			}
		}

		return multiSkus;
	}

	@Test
	public void test5() {
		String test = "dafscopy_f";
		System.out.println(genaretCopyTemplateName(genaretCopyTemplateName(test)));
	}

	private String genaretCopyTemplateName(String templateName) {

		if (templateName.contains("copy")) {
			templateName.lastIndexOf("_");
			String prefix = templateName.substring(0, templateName.lastIndexOf("_") + 1);
			try {
				int seq = Integer
						.valueOf(templateName.substring(templateName.lastIndexOf("_") + 1, templateName.length())) + 1;
				return prefix + seq;
			} catch (NumberFormatException e) {
			}
		}
		return templateName + "_copy_0";
	}
	
	@Test
	public void test4()  {
		try {
			int num = 8/0;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(e.getLocalizedMessage());
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(e.getCause());
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(e.getStackTrace());
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(e.toString());
		}
	}
	
	
	
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
		System.out.println("adfs.fasd".replaceAll("\\.", "_"));
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
