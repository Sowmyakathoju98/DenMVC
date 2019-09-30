package com.virtusa.denorm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class OrderDetail {
	@Id
	@GeneratedValue
	private int orderId;
	@NotNull
	private int customerId;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private ProductDetail product;
	@NotNull
	private int orderQuantity;
	@NotNull
	private float orderTotalAmount;
	@NotNull
	private int orderStatus;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public ProductDetail getProduct() {
		return product;
	}

	public void setProduct(ProductDetail product) {
		this.product = product;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public float getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(float orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", customer=" + customerId + ", product=" + product
				+ ", orderQuantity=" + orderQuantity + ", orderTotalAmount=" + orderTotalAmount + ", orderStatus="
				+ orderStatus + "]";
	}

	public OrderDetail(int customerId, ProductDetail product, int orderQuantity, float orderTotalAmount,
			int orderStatus) {
		super();
		this.customerId = customerId;
		this.product = product;
		this.orderQuantity = orderQuantity;
		this.orderTotalAmount = orderTotalAmount;
		this.orderStatus = orderStatus;
	}

	public OrderDetail() {
	}

}
