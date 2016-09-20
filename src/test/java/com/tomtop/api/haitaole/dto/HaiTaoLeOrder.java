package com.tomtop.api.haitaole.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="channel")
public class HaiTaoLeOrder {

	private String title;
	private HaiTaoLeOrderItem item;

	public HaiTaoLeOrder() {
		super();
	}

	public HaiTaoLeOrder(String title, HaiTaoLeOrderItem item) {
		super();
		this.title = title;
		this.item = item;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HaiTaoLeOrderItem getItem() {
		return item;
	}

	public void setItem(HaiTaoLeOrderItem item) {
		this.item = item;
	}

}
