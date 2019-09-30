package com.virtusa.denorm.dao;

import java.util.List;

import com.virtusa.denorm.entity.CardDetail;
import com.virtusa.denorm.entity.CategoryDetail;
import com.virtusa.denorm.entity.CustomerDetail;
import com.virtusa.denorm.entity.LoginDetail;
import com.virtusa.denorm.entity.OrderDetail;
import com.virtusa.denorm.entity.ProductDetail;
import com.virtusa.denorm.entity.ShoppingCart;
import com.virtusa.denorm.exception.ShoppingException;

public interface DAOInterface {
	public int doRegister(CustomerDetail customer) throws ShoppingException;

	public List<CustomerDetail> doLogin(LoginDetail login) throws ShoppingException;

	public List<CategoryDetail> getAllCategory() throws ShoppingException;

	public List<ProductDetail> doSearch(String searchItem) throws ShoppingException;

	public int doOrder(OrderDetail details) throws ShoppingException;

	public int doPayment(CardDetail cardDetail) throws ShoppingException;

	public int doAddToCart(ShoppingCart cart) throws ShoppingException;

	public List<ShoppingCart> viewCart(int customerId) throws ShoppingException;

	public int doDeleteCartItem(int cartId) throws ShoppingException;

	public int doAddCategory(String name) throws ShoppingException;

	public int doDeleteCategory(CategoryDetail categoryDetail) throws ShoppingException;

	public int doAddProduct(ProductDetail productDetail) throws ShoppingException;

	public List<ProductDetail> doListProduct(CategoryDetail categoryDetail) throws ShoppingException;

	public int doDeleteProduct(int productid) throws ShoppingException;

	public List<ProductDetail> doViewProducts() throws ShoppingException;

}
