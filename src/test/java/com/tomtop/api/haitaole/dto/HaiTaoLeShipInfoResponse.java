package com.tomtop.api.haitaole.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class HaiTaoLeShipInfoResponse {

	private String status;
	private String message;
	private String request_time;
	private List<HaiTaoLeShippingInfo> shipping_info;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequest_time() {
		return request_time;
	}

	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}

	@XmlElementWrapper(name = "shipping_info")  
	@XmlElement(name = "shipping")
	public List<HaiTaoLeShippingInfo> getShipping_info() {
		return shipping_info;
	}

	public void setShipping_info(List<HaiTaoLeShippingInfo> shipping_info) {
		this.shipping_info = shipping_info;
	}

}
