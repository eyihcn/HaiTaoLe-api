package com.tomtop.api.haitaole.dto;

public class HaiTaoLeOrderProduct {

	private String products_id; // 
	private String sku; // sku
	private String products_qty; // 商品数量
	private String sales_price; // 代理单价

	public HaiTaoLeOrderProduct() {
		super();
	}

	public HaiTaoLeOrderProduct(String products_id,String sku, String products_qty, String sales_price) {
		super();
		this.products_id = products_id;
		this.sku = sku;
		this.products_qty = products_qty;
		this.sales_price = sales_price;
	}

	public String getProducts_id() {
		return products_id;
	}

	public void setProducts_id(String products_id) {
		this.products_id = products_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProducts_qty() {
		return products_qty;
	}

	public void setProducts_qty(String products_qty) {
		this.products_qty = products_qty;
	}

	public String getSales_price() {
		return sales_price;
	}

	public void setSales_price(String sales_price) {
		this.sales_price = sales_price;
	}

}
