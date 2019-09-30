package com.virtusa.denorm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CategoryDetail {
	@Override
	public String toString() {
		return "CategoryDetail [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

	@Id
	@GeneratedValue
	private int categoryId;
//	@NotNull
	private String categoryName;

	public CategoryDetail(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public CategoryDetail(int categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public CategoryDetail() {
	}

	public CategoryDetail(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

}
