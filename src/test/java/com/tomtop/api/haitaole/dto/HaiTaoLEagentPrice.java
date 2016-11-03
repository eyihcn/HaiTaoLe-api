package com.tomtop.api.haitaole.dto;

public class HaiTaoLEagentPrice {

	private int start_quantity; // 商品数量区间（开始值）
	private int end_quantity; // 商品数量区间（结束值）
	private float price; // 商品数量区间对应的代理价

	public int getStart_quantity() {
		return start_quantity;
	}

	public void setStart_quantity(int start_quantity) {
		this.start_quantity = start_quantity;
	}

	public int getEnd_quantity() {
		return end_quantity;
	}

	public void setEnd_quantity(int end_quantity) {
		this.end_quantity = end_quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
