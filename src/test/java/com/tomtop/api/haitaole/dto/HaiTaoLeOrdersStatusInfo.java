package com.tomtop.api.haitaole.dto;


public class HaiTaoLeOrdersStatusInfo {

	private String orders_model;// 系统订单号
	private String custom_orders_number; // 自定义订单号
	private String gmt_modified; // datetime 订单更新时间
	private String orders_status_code; // 订单状态编码
	private String orders_status;// 订单状态
	// 1：处理中、 6：待推送、 7：已推送、 9：已发货、5：审核不通过、14：推送失败、
	// 8：出库失败、 10：交易关闭、 22：重新推送、 20：出库异常
	private String payment_code; // 支付状态编码
	private String payment_status; // 支付状态 11：待付款、 12：待确认、 15：预付款、 2：已付款

	public String getOrders_model() {
		return orders_model;
	}

	public String getCustom_orders_number() {
		return custom_orders_number;
	}

	public String getGmt_modified() {
		return gmt_modified;
	}

	public String getOrders_status_code() {
		return orders_status_code;
	}

	public String getOrders_status() {
		return orders_status;
	}

	public String getPayment_code() {
		return payment_code;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setOrders_model(String orders_model) {
		this.orders_model = orders_model;
	}

	public void setCustom_orders_number(String custom_orders_number) {
		this.custom_orders_number = custom_orders_number;
	}

	public void setGmt_modified(String gmt_modified) {
		this.gmt_modified = gmt_modified;
	}

	public void setOrders_status_code(String orders_status_code) {
		this.orders_status_code = orders_status_code;
	}

	public void setOrders_status(String orders_status) {
		this.orders_status = orders_status;
	}

	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

}
