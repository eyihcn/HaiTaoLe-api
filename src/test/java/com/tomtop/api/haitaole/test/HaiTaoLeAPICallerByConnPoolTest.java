package com.tomtop.api.haitaole.test;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.tomtop.api.haitaole.HaiTaoLeAPICallerByConnPool;
import com.tomtop.api.haitaole.dto.HaiTaoLeQueryShipOrdersInfo;

@ContextConfiguration(locations = { "classpath:/applicationContext-test.xml" })
public class HaiTaoLeAPICallerByConnPoolTest extends AbstractJUnit4SpringContextTests {

	final Logger log = LoggerFactory.getLogger(HaiTaoLeAPICallerByConnPoolTest.class);

	@Autowired
	@Qualifier("haiTaoLeAPICallerByConnPool")
	HaiTaoLeAPICallerByConnPool haiTaoLeAPICallerByConnPool;

	long start;

	@Before
	public void setup() {
		log.info("=========================== setup ~");
		start = System.currentTimeMillis();
	}

	@After
	public void teardown() {
		log.info("~~~~~~~~~~ test cost time ms : " + (System.currentTimeMillis() - start));
		log.info("=========================== teardown ~");
	}

	@Test
	public void testPushOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryShip() {
		HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo = new HaiTaoLeQueryShipOrdersInfo(null,
				"20150917125425490");
		haiTaoLeAPICallerByConnPool.queryShip(Arrays.asList(haiTaoLeQueryShipOrdersInfo));
	}
	
	@Test
	public void testQueryShip1000Times() {
		int times = 500;
		for (int i=1; i<times; i++) {
			HaiTaoLeQueryShipOrdersInfo haiTaoLeQueryShipOrdersInfo = new HaiTaoLeQueryShipOrdersInfo(null,"20150917125425490");
			haiTaoLeAPICallerByConnPool.queryShip(Arrays.asList(haiTaoLeQueryShipOrdersInfo));
			System.out.println(i);
		}
	}

}
