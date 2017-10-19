package com.tomtop.api.haitaole.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SortKeyOrValue {

	@Test
	public void test() {
		new SortKeyOrValue().printSeq_value("DV7^10,DV8^20,DV9,DV8^2");
		new SortKeyOrValue().printSeq_key("DV7^10,DV8^20,DV9,DV8^2");
	}

	public void printSeq_value(String skuStr) {
		System.out.println("=========sort qty=========");
		Map<String, SkuQtyEntity> skuToSkuQtyEntity = parseMultiSku(skuStr);
		List<SkuQtyEntity> skuQtyEntityCol = new ArrayList<SkuQtyEntity>(skuToSkuQtyEntity.values());
		Collections.sort(skuQtyEntityCol, new Comparator<SkuQtyEntity>() {

			// 返回0代表相等，大于0代表o1大于o2
			public int compare(SkuQtyEntity o1, SkuQtyEntity o2) {
				// 若o1的qty 》 o2的qty，则认为 o1>o2
				return o1.getQty() - o2.getQty();
			}

		});
		print(skuQtyEntityCol);
	}

	public void printSeq_key(String skuStr) {
		System.out.println("=========sort sku=========");
		Map<String, SkuQtyEntity> skuToSkuQtyEntity = parseMultiSku(skuStr);
		List<SkuQtyEntity> skuQtyEntityCol = new ArrayList<SkuQtyEntity>(skuToSkuQtyEntity.values());
		Collections.sort(skuQtyEntityCol, new Comparator<SkuQtyEntity>() {

			// 返回0代表相等，大于0代表o1大于o2
			public int compare(SkuQtyEntity o1, SkuQtyEntity o2) {
				// 若o1的qty 》 o2的qty，则认为 o1>o2
				return o1.getSku().compareTo(o2.getSku());
			}

		});
		print(skuQtyEntityCol);

	}

	public Map<String, SkuQtyEntity> parseMultiSku(String skuStr) {

		Map<String, SkuQtyEntity> multiSkus = new HashMap<String, SkuQtyEntity>();

		String[] skuMultiQtys = skuStr.split(",");

		for (String skuMultiQty : skuMultiQtys) {
			String[] skuQty = skuMultiQty.split("\\^");
			String sku = skuQty[0].trim();
			int qty = 1;
			if (skuQty.length == 2) {
				String qtyStr = skuQty[1];
				if (!qtyStr.matches("\\d+")) {
					qty = 0;
				} else {
					qty = Integer.valueOf(qtyStr);
				}
			}
			SkuQtyEntity skuQtyEntity = multiSkus.get(sku);
			if (null != skuQtyEntity) {
				skuQtyEntity.addQty(qty);
			} else {
				multiSkus.put(sku, new SkuQtyEntity(sku, qty));
			}
		}

		return multiSkus;
	}

	public void print(List<SkuQtyEntity> skuQtyEntityCol) {
		for (SkuQtyEntity entity : skuQtyEntityCol) {
			System.out.println(entity.getSku() + "  :  " + entity.getQty());
		}
	}

	class SkuQtyEntity {

		private String sku;
		private int qty;

		public SkuQtyEntity(String sku) {
			this.sku = sku;
			this.qty = 1;
		}

		public SkuQtyEntity addQty(int offset) {
			this.qty += offset;
			return this;
		}

		public SkuQtyEntity(String sku, int qty) {
			this.sku = sku;
			this.qty = qty;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		@Override
		public String toString() {
			return "SkuQtyEntity [sku=" + sku + ", qty=" + qty + "]";
		}

	}
}
