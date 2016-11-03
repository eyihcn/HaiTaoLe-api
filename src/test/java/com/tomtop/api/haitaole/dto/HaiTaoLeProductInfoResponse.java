package com.tomtop.api.haitaole.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class HaiTaoLeProductInfoResponse {

	private int status; // 响应状态
	private String message; // 响应消息
	private String request_time;
	private List<HaiTaoLeProduct> products_list;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	@XmlElementWrapper(name = "products_list")  
	@XmlElement(name = "product")
	public List<HaiTaoLeProduct> getProducts_list() {
		return products_list;
	}

	public void setProducts_list(List<HaiTaoLeProduct> products_list) {
		this.products_list = products_list;
	}

}
