package com.tomtop.api.haitaole.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="order")
public class HaiTaoLeQueryOrderStatusInfo {

	private String orders_model;
	private String custom_orders_number;

	public HaiTaoLeQueryOrderStatusInfo() {
		super();
	}

	public HaiTaoLeQueryOrderStatusInfo(String orders_model, String custom_orders_number) {
		super();
		this.orders_model = orders_model;
		this.custom_orders_number = custom_orders_number;
	}

	public String getOrders_model() {
		return orders_model;
	}

	public String getCustom_orders_number() {
		return custom_orders_number;
	}

	public void setOrders_model(String orders_model) {
		this.orders_model = orders_model;
	}

	public void setCustom_orders_number(String custom_orders_number) {
		this.custom_orders_number = custom_orders_number;
	}

}
