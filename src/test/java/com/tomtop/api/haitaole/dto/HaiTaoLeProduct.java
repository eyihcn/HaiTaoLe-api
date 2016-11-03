package com.tomtop.api.haitaole.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class HaiTaoLeProduct {

	private int products_id;// 对应海淘乐系统商品ID
	private String sku; // 商品唯一编码
	private String products_name;
	private List<HaiTaoLEagentPrice> agent_prices; // 代理价:
													// 海淘乐给各合作商户的代理价(如果没有价格则为空)
	private String market_price; // 市场价
	private int quantity; // 商品库存
	private int first_categories_id; // 一级类目ID: 对应海淘乐系统的一级类目ID
	private Integer second_categories_id; // 二级类目ID: 对应海淘乐系统的二级类目ID 非必填
	private float weight; // 商品的重量，单位克(g)
	private String express_id; // 运费ID: 对应海淘乐系统该的运费信息ID，包邮的情况下为0，如果多个运费，就用逗号隔开
	private int store_expenses_id; // 仓储费ID: 对应海淘乐系统该的仓储费信息ID，包邮或无仓储费的情况下为0
	private int store_type; // 库存方式 1 – 保税仓 ; 2 – 直邮 ;3 – 现货
	private int status; // 商品状态 1 – 上架 ;2 – 下架
	private String producing_area; // 商品原产地
	private String brief_description; // 非必填 商品简要描述
	private String description; // 商品详细描述
	private String main_image_url; // 商品主图
	private String image_url; // 非必填 商品附图 多个图片链接以‘|’分割

	public int getProducts_id() {
		return products_id;
	}

	public void setProducts_id(int products_id) {
		this.products_id = products_id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProducts_name() {
		return products_name;
	}

	public void setProducts_name(String products_name) {
		this.products_name = products_name;
	}
	
	@XmlElementWrapper(name = "agent_prices")  
	@XmlElement(name = "agent_price")
	public List<HaiTaoLEagentPrice> getAgent_prices() {
		return agent_prices;
	}

	public void setAgent_prices(List<HaiTaoLEagentPrice> agent_prices) {
		this.agent_prices = agent_prices;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getFirst_categories_id() {
		return first_categories_id;
	}

	public void setFirst_categories_id(int first_categories_id) {
		this.first_categories_id = first_categories_id;
	}

	public Integer getSecond_categories_id() {
		return second_categories_id;
	}

	public void setSecond_categories_id(Integer second_categories_id) {
		this.second_categories_id = second_categories_id;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getExpress_id() {
		return express_id;
	}

	public void setExpress_id(String express_id) {
		this.express_id = express_id;
	}

	public int getStore_expenses_id() {
		return store_expenses_id;
	}

	public void setStore_expenses_id(int store_expenses_id) {
		this.store_expenses_id = store_expenses_id;
	}

	public int getStore_type() {
		return store_type;
	}

	public void setStore_type(int store_type) {
		this.store_type = store_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProducing_area() {
		return producing_area;
	}

	public void setProducing_area(String producing_area) {
		this.producing_area = producing_area;
	}

	public String getBrief_description() {
		return brief_description;
	}

	public void setBrief_description(String brief_description) {
		this.brief_description = brief_description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMain_image_url() {
		return main_image_url;
	}

	public void setMain_image_url(String main_image_url) {
		this.main_image_url = main_image_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
