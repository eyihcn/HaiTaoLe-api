package com.tomtop.api.haitaole.dto;

public class HaiTaoLeShippingInfo {

	private String orders_model; // 系统订单号
	private String custom_orders_number; // 自定义订单号
	private String express_number; // 物流单号
	private String express_name; // 货运公司

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

	public String getExpress_number() {
		return express_number;
	}

	public void setExpress_number(String express_number) {
		this.express_number = express_number;
	}

	public String getExpress_name() {
		return express_name;
	}

	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}

}
