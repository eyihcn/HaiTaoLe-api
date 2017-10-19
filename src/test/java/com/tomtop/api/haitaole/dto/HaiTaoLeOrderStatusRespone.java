package com.tomtop.api.haitaole.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="response")
public class HaiTaoLeOrderStatusRespone {

	private Integer status; // 200: 查询成功
	// 300: 您不是合法用户
	// 400: 请求参数填写不完整
	// 500: 服务器错误,请过段时间查询
	private String message; // 提示信息
	private String request_time; // yyyy-MM-dd hh:mm:ss
	private List<HaiTaoLeOrdersStatusInfo> orders_info; // 返回订单状态信息

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getRequest_time() {
		return request_time;
	}

	@XmlElementWrapper(name = "orders_info")  
	@XmlElement(name = "order")
	public List<HaiTaoLeOrdersStatusInfo> getOrders_info() {
		return orders_info;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	public void setOrders_info(List<HaiTaoLeOrdersStatusInfo> orders_info) {
		this.orders_info = orders_info;
	}

}
