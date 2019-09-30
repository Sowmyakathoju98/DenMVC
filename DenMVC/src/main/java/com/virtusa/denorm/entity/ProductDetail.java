package com.virtusa.denorm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductDetail {
	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	
	private String productImage;

	private float productPrice;
	private String productDescription;
	
	private int productStock;

	private int categoryId;

	public ProductDetail(int productId) {
		super();
		this.productId = productId;
	}

	public ProductDetail(int productId, String productName, String productImage, float productPrice,
			String productDescription) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
	}

	public ProductDetail(String productName, String productImage, float productPrice, String productDescription,
			int productStock, int categoryId) {
		super();

		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productStock = productStock;
		this.categoryId = categoryId;
	}

	public ProductDetail(int productId, String productName, String productImage, float productPrice,
			String productDescription, int productStock, int categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productStock = productStock;
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductDetail [productId=" + productId + ", productName=" + productName + ", productImage="
				+ productImage + ", productPrice=" + productPrice + ", productDescription=" + productDescription
				+ ", productStock=" + productStock + ", category=" + categoryId + "]";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategory(int categoryId) {
		this.categoryId = categoryId;
	}

	public ProductDetail() {
	}

}
