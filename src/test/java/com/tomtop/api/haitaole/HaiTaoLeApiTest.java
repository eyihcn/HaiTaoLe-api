package com.tomtop.api.haitaole;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class HaiTaoLeApiTest extends AbstractJUnit4SpringContextTests {

	RestTemplate restTemplate ;
	
	
}
