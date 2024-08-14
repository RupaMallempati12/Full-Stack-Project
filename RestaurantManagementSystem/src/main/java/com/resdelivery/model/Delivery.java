package com.resdelivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delivery")
public class Delivery {
	
	@Id
	String id;
	String orderId;
	String staffId;
	String dtime;
	String status;
	

	public Delivery() {
		super();
	}


	public Delivery(String id, String orderId, String staffId, String dtime, String status) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.staffId = staffId;
		this.dtime = dtime;
		this.status = status;
	}


	@Override
	public String toString() {
		return "Delivery [id=" + id + ", orderId=" + orderId + ", staffId=" + staffId + ", dtime=" + dtime + ", status="
				+ status + "]";
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


	public String getDtime() {
		return dtime;
	}


	public void setDtime(String dtime) {
		this.dtime = dtime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
}
