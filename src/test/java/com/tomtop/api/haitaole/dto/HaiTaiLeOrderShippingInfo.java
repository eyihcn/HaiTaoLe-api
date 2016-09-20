package com.tomtop.api.haitaole.dto;

public class HaiTaiLeOrderShippingInfo {

	private String shipping_telephone; // 收货人手机号码
	private String shipping_customers_name; // 收货人姓名
	private String shipping_customers_identity; // 身份证号码,直邮、现货订单选填；保税仓订单必填；
	private String shipping_customers_identity_name; // 身份证姓名,直邮、现货订单选填；保税仓订单必填；

	public HaiTaiLeOrderShippingInfo() {
		super();
	}

	public HaiTaiLeOrderShippingInfo(String shipping_telephone, String shipping_customers_name,
			String shipping_customers_identity, String shipping_customers_identity_name) {
		super();
		this.shipping_telephone = shipping_telephone;
		this.shipping_customers_name = shipping_customers_name;
		this.shipping_customers_identity = shipping_customers_identity;
		this.shipping_customers_identity_name = shipping_customers_identity_name;
	}

	public String getShipping_telephone() {
		return shipping_telephone;
	}

	public void setShipping_telephone(String shipping_telephone) {
		this.shipping_telephone = shipping_telephone;
	}

	public String getShipping_customers_name() {
		return shipping_customers_name;
	}

	public void setShipping_customers_name(String shipping_customers_name) {
		this.shipping_customers_name = shipping_customers_name;
	}

	public String getShipping_customers_identity() {
		return shipping_customers_identity;
	}

	public void setShipping_customers_identity(String shipping_customers_identity) {
		this.shipping_customers_identity = shipping_customers_identity;
	}

	public String getShipping_customers_identity_name() {
		return shipping_customers_identity_name;
	}

	public void setShipping_customers_identity_name(String shipping_customers_identity_name) {
		this.shipping_customers_identity_name = shipping_customers_identity_name;
	}

}
