package com.resdelivery.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {
	
	@Id
	String id;
	String orderId;
	String custId;
	List<OrderItem> orderItems;
	String orderedDate;
	double amount;
	String orderType;
	String deliveryStatus;
	String paymentmode;
	String paymentstatus;
	String staffId;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String id, String orderId, String custId, List<OrderItem> orderItems, String orderedDate,
			double amount, String orderType, String deliveryStatus, String paymentmode, String paymentstatus,
			String staffId) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.custId = custId;
		this.orderItems = orderItems;
		this.orderedDate = orderedDate;
		this.amount = amount;
		this.orderType = orderType;
		this.deliveryStatus = deliveryStatus;
		this.paymentmode = paymentmode;
		this.paymentstatus = paymentstatus;
		this.staffId = staffId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", custId=" + custId + ", orderItems=" + orderItems
				+ ", orderedDate=" + orderedDate + ", amount=" + amount + ", orderType=" + orderType
				+ ", deliveryStatus=" + deliveryStatus + ", paymentmode=" + paymentmode + ", paymentstatus="
				+ paymentstatus + ", staffId=" + staffId + "]";
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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	
}
