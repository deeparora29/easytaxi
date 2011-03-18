package com.btit.utilsweb.test.table.vo;

import java.io.Serializable;
/**
 * 映射Table"ROLLUP_ENTERPRISE_COMMBARCODE.txt"
 * @author renmian
 *
 */
public class RollupEnterpriseCommbarcode implements Serializable {
	/**
	 * 条码
	 */
	private String commBarcode;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 地区编码
	 */
	private String areaCode;
	/**
	 * 种类编码
	 */
	private String commSort;
	/**
	 * 销售时间
	 */
	private String saleTime;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 数量
	 */
	private String amount;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 总价
	 */
	private String sumPrice;
	public String getCommBarcode() {
		return commBarcode;
	}
	public void setCommBarcode(String commBarcode) {
		this.commBarcode = commBarcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCommSort() {
		return commSort;
	}
	public void setCommSort(String commSort) {
		this.commSort = commSort;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

}
