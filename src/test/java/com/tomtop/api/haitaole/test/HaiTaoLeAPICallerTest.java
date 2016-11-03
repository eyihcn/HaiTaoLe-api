package com.tomtop.api.haitaole.test;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.tomtop.api.haitaole.HaiTaoLeAPICaller;
import com.tomtop.api.haitaole.dto.HaiTaoLeProductInfoResponse;
import com.tomtop.api.haitaole.dto.HaiTaoLeQueryShipOrdersInfo;
import com.tomtop.api.utils.JaxbUtil;


@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
public class HaiTaoLeAPICallerTest extends AbstractJUnit4SpringContextTests {

	final Logger log = LoggerFactory.getLogger(HaiTaoLeAPICallerTest.class);
	
	@Autowired
	@Qualifier("haiTaoLeAPICaller")
	HaiTaoLeAPICaller haiTaoLeAPICaller;
	
	long start;
	
	@Before
	public void setup() {
		log.info("=========================== setup ~");
		start = System.currentTimeMillis();
	}
	
	@After
	public void teardown(){
		log.info("~~~~~~~~~~ test cost time ms : " + (System.currentTimeMillis()-start));
		log.info("=========================== teardown ~");
	}
	
	
	@Test
	public void testQueryHaiTaoLeProductInfo() throws JAXBException {
		Set<String> skus = new HashSet<String>();
		skus.add("1027004169");
//		skus.add("1046003765");
		// 1005004161  1046003765
		String result = haiTaoLeAPICaller.queryHaiTaoLeProductInfo(skus);
		JAXBContext jAXBContext = JAXBContext.newInstance(HaiTaoLeProductInfoResponse.class);
		HaiTaoLeProductInfoResponse resp = JaxbUtil.xmlToBean(result, jAXBContext);
		System.out.println(resp.getProducts_list().get(0).getAgent_prices().get(0).getPrice());
	}
	
	@Test
	public void testPushOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryShip() {
		// 43745261   43703025   43703026  43702283

//		HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo = new HaiTaoLeQueryShipOrdersInfo(null,"43648758");
		HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo1 = new HaiTaoLeQueryShipOrdersInfo(null,"43702283");
		haiTaoLeAPICaller.queryShip(Arrays.asList(haiTaoLeQueryShipOrdersInfo1));
	}
	
	@Test
	public void testQueryShip1000Times() {
		int times = 500;
		for (int i=1; i<times; i++) {
			HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo = new HaiTaoLeQueryShipOrdersInfo(null,"20150917125425490");
			haiTaoLeAPICaller.queryShip(Arrays.asList(haiTaoLeQueryShipOrdersInfo));
		}
	}

}
