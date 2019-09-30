package com.virtusa.denorm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.virtusa.denorm.dao.DAOInterface;
import com.virtusa.denorm.entity.CardDetail;
import com.virtusa.denorm.entity.CategoryDetail;
import com.virtusa.denorm.entity.CustomerDetail;
import com.virtusa.denorm.entity.LoginDetail;
import com.virtusa.denorm.entity.OrderDetail;
import com.virtusa.denorm.entity.ProductDetail;
import com.virtusa.denorm.entity.ShoppingCart;
import com.virtusa.denorm.exception.ShoppingException;

@Service
public class ShoppingService implements ServiceInterface {
	private DAOInterface dao;

	public void setDao(DAOInterface dao) {
		this.dao = dao;
	}

	@Transactional
	public int doRegister(CustomerDetail customer) throws ShoppingException {
		return dao.doRegister(customer);
	}

	@Transactional
	public List<CustomerDetail> doLogin(LoginDetail login) throws ShoppingException {
		return dao.doLogin(login);
	}

	@Transactional
	public int doAddCategory(String name) throws ShoppingException {
		return dao.doAddCategory(name);
	}

	@Transactional
	public List<CategoryDetail> getAllCategory() throws ShoppingException {
		return dao.getAllCategory();
	}

	@Transactional
	public int doAddProduct(ProductDetail productDetail) throws ShoppingException {

		return dao.doAddProduct(productDetail);
	}

	@Transactional
	public int doDeleteCategory(CategoryDetail categoryDetail) throws ShoppingException {
		return dao.doDeleteCategory(categoryDetail);
	}

	@Transactional
	public List<ProductDetail> doListProduct(CategoryDetail categoryDetail) throws ShoppingException {

		return dao.doListProduct(categoryDetail);
	}

	@Transactional
	public int doDeleteProduct(int productid) throws ShoppingException {

		return dao.doDeleteProduct(productid);
	}

	@Transactional
	public List<ProductDetail> doViewProducts() throws ShoppingException {

		return dao.doViewProducts();
	}

	@Transactional
	public List<ProductDetail> doSearch(String searchItem) throws ShoppingException {
		return dao.doSearch(searchItem);
	}

	@Transactional
	public int doAddToCart(ShoppingCart cart) throws ShoppingException {

		return dao.doAddToCart(cart);
	}

	@Transactional
	public List<ShoppingCart> viewCart(int customerId) throws ShoppingException {
		return dao.viewCart(customerId);
	}

	@Transactional
	public int doDeleteCartItem(int cartId) throws ShoppingException {
		return dao.doDeleteCartItem(cartId);
	}

	@Transactional
	public int doPayment(CardDetail cardDetail) throws ShoppingException {
		return dao.doPayment(cardDetail);
	}

	@Transactional
	public int doOrder(OrderDetail details) throws ShoppingException {
		return dao.doOrder(details);
	}

}
