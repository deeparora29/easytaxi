package com.btit.utilsweb.test.table.vo;

import java.io.Serializable;

/**
 * 用于测试显示table数据
 * 映射Table“Staff_info.txt”
 * @author lenovo
 *
 */
public class StaffInfo implements Serializable {
	
	private String staffId;
	
	private String staffName;
	
	private String departName;
	
	public StaffInfo(){
		super();
	}
	
	public StaffInfo(String staffId, String staffName, String departName){
		this.staffId = staffId;
		this.staffName = staffName;
		this.departName = departName;
	}
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}
	
}
