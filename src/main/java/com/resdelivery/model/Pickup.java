package com.resdelivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pickup")
public class Pickup {
	
	@Id
	String id;
	String orderId;
	String staffId;
	String ptime;
	String status;
	

	public Pickup() {
		super();
	}


	public Pickup(String id, String orderId, String staffId, String ptime, String status) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.staffId = staffId;
		this.ptime = ptime;
		this.status = status;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public String getPtime() {
		return ptime;
	}


	public void setPtime(String ptime) {
		this.ptime = ptime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Pickup [id=" + id + ", orderId=" + orderId + ", staffId=" + staffId + ", ptime=" + ptime + ", status="
				+ status + "]";
	}


	
}
