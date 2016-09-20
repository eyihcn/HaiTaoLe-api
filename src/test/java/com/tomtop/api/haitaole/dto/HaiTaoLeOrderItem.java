package com.tomtop.api.haitaole.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class HaiTaoLeOrderItem {

	private String custom_orders_number; // 自定义订单号
	private String shipping_province; // 收货省份名称
	private String shipping_city; // 收货城市名称
	private String shipping_district; // 收货区县名称
	private String shipping_address; // 收货街道地址
	private String shipping_postcode; // 收货地址邮编,非必填
	private String comments; // 订单备注,非必填
	private String express_id; // 运费ID,若无运费ID则填0
	private String express_amount; // 运费金额
	private String store_expenses_id; // 仓储费ID,若无仓储费ID则填0
	private String store_amount; // 仓储费金额
	private List<HaiTaoLeOrderProduct> order_products;// 订单商品信息,订单商品信息(必须有一条商品信息)
	private String alliances_payment_date; // 支付时间,日期格式(yyyy-MM-dd hh:mm:ss),非必填
	private String alliances_payment_method; // 支付方式,非必填
	private String alliances_payment_serial; // 支付流水号,非必填
	private List<HaiTaiLeOrderShippingInfo> order_shipping_info;// 订单收货身份信息(必须要有一条收货身份信息)

	public String getCustom_orders_number() {
		return custom_orders_number;
	}

	public void setCustom_orders_number(String custom_orders_number) {
		this.custom_orders_number = custom_orders_number;
	}

	public String getShipping_province() {
		return shipping_province;
	}

	public void setShipping_province(String shipping_province) {
		this.shipping_province = shipping_province;
	}

	public String getShipping_city() {
		return shipping_city;
	}

	public void setShipping_city(String shipping_city) {
		this.shipping_city = shipping_city;
	}

	public String getShipping_district() {
		return shipping_district;
	}

	public void setShipping_district(String shipping_district) {
		this.shipping_district = shipping_district;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}

	public String getShipping_postcode() {
		return shipping_postcode;
	}

	public void setShipping_postcode(String shipping_postcode) {
		this.shipping_postcode = shipping_postcode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getExpress_id() {
		return express_id;
	}

	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}

	public String getExpress_amount() {
		return express_amount;
	}

	public void setExpress_amount(String express_amount) {
		this.express_amount = express_amount;
	}

	public String getStore_expenses_id() {
		return store_expenses_id;
	}

	public void setStore_expenses_id(String store_expenses_id) {
		this.store_expenses_id = store_expenses_id;
	}

	public String getStore_amount() {
		return store_amount;
	}

	public void setStore_amount(String store_amount) {
		this.store_amount = store_amount;
	}

	@XmlElementWrapper(name = "order_products")  
	@XmlElement(name = "product") 
	public List<HaiTaoLeOrderProduct> getOrder_products() {
		return order_products;
	}

	public void setOrder_products(List<HaiTaoLeOrderProduct> order_products) {
		this.order_products = order_products;
	}

	public String getAlliances_payment_date() {
		return alliances_payment_date;
	}

	public void setAlliances_payment_date(String alliances_payment_date) {
		this.alliances_payment_date = alliances_payment_date;
	}

	public String getAlliances_payment_method() {
		return alliances_payment_method;
	}

	public void setAlliances_payment_method(String alliances_payment_method) {
		this.alliances_payment_method = alliances_payment_method;
	}

	public String getAlliances_payment_serial() {
		return alliances_payment_serial;
	}

	public void setAlliances_payment_serial(String alliances_payment_serial) {
		this.alliances_payment_serial = alliances_payment_serial;
	}

	@XmlElementWrapper(name = "order_shipping_info")  
	@XmlElement(name = "shipping_info")
	public List<HaiTaiLeOrderShippingInfo> getOrder_shipping_info() {
		return order_shipping_info;
	}

	public void setOrder_shipping_info(List<HaiTaiLeOrderShippingInfo> order_shipping_info) {
		this.order_shipping_info = order_shipping_info;
	}

}
