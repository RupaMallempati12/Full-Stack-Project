package com.resdelivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staff")
public class Staff {
	
	@Id
	String id;
	String staffId;
	String name;
	String address;
	String phone;
	String email;
	String gender;
	double salary;
	String stafftype;
	String password;
	
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Staff(String id, String staffId, String name, String address, String phone, String email, String gender,
			double salary, String stafftype, String password) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.salary = salary;
		this.stafftype = stafftype;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Staff [id=" + id + ", staffId=" + staffId + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", gender=" + gender + ", salary=" + salary + ", stafftype=" + stafftype
				+ ", password=" + password + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public String getStafftype() {
		return stafftype;
	}


	public void setStafftype(String stafftype) {
		this.stafftype = stafftype;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
}
