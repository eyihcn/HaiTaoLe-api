package com.tomtop.api.haitaole.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Administrator 系统订单号和自定义订单号两个必须存在一个，如两个都存在，就以系统订单号为查询条件；一次请求不能超过50个订单
 */
@XmlRootElement(name="order")
public class HaiTaoLeQueryShipOrdersInfo {

	private String orders_model; // 系统订单号
	private String custom_orders_number; // 自定义订单号

	public HaiTaoLeQueryShipOrdersInfo() {
		super();
	}

	public HaiTaoLeQueryShipOrdersInfo(String orders_model, String custom_orders_number) {
		super();
		this.orders_model = orders_model;
		this.custom_orders_number = custom_orders_number;
	}

	public String getOrders_model() {
		return orders_model;
	}

	public void setOrders_model(String orders_model) {
		this.orders_model = orders_model;
	}

	public String getCustom_orders_number() {
		return custom_orders_number;
	}

	public void setCustom_orders_number(String custom_orders_number) {
		this.custom_orders_number = custom_orders_number;
	}

}
