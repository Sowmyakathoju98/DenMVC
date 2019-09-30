package com.virtusa.denorm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@IdClass(CompositePrimary.class)
public class CustomerDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int customerId;
	@NotBlank(message="Name cannot be Null")
	private String customerName;
	@Id
	@Email
	@NotBlank(message="Email cannot be Null")
	private String customerEmail;
	
	@Size(min = 6, message = "Password must be minimum 6 digits")
	private String customerPassword;
	@NotBlank(message="DOB cannot be Null")
	private String customerDOB;
	@NotBlank(message="Address cannot be Null")
	private String customerAddress;
	private long customerContact;
	@NotBlank(message="State cannot be Null")
	private String customerState;
	private String customerCity;

	public CustomerDetail(int customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public CustomerDetail(int customerId) {
		super();
		this.customerId = customerId;
	}

	public CustomerDetail() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerDetail [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPassword=" + customerPassword + ", customerDOB=" + customerDOB
				+ ", customerAddress=" + customerAddress + ", customerContact=" + customerContact + ", customerState="
				+ customerState + ", customerCity=" + customerCity + "]";
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(String customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public long getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(long customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public CustomerDetail(String customerName, String customerEmail, String customerPassword, String customerDOB,
			String customerAddress, long customerContact, String customerState, String customerCity) {
		super();

		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.customerDOB = customerDOB;
		this.customerAddress = customerAddress;
		this.customerContact = customerContact;
		this.customerState = customerState;
		this.customerCity = customerCity;
	}

}
