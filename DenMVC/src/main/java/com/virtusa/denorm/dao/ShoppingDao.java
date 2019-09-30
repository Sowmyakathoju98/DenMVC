package com.virtusa.denorm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.virtusa.denorm.entity.CardDetail;
import com.virtusa.denorm.entity.CategoryDetail;
import com.virtusa.denorm.entity.CustomerDetail;
import com.virtusa.denorm.entity.LoginDetail;
import com.virtusa.denorm.entity.OrderDetail;
import com.virtusa.denorm.entity.ProductDetail;
import com.virtusa.denorm.entity.ShoppingCart;
import com.virtusa.denorm.exception.ShoppingException;

@Repository
public class ShoppingDao implements DAOInterface {
	private SessionFactory sessionFactory;
	Logger logger = Logger.getLogger("ShoppingDao");

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int doRegister(CustomerDetail customer) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		LoginDetail user = new LoginDetail(customer.getCustomerEmail(), customer.getCustomerPassword(), "USER", true);
		session.save(user);
		session.save(customer);
		logger.info("customer "+customer.getCustomerName()+" registered successfully");
		return customer.getCustomerId();
	}

	public List<CustomerDetail> doLogin(LoginDetail login) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("logoin:" + login);
		List<CustomerDetail> list = null;
		Query query = session.createQuery(
				"from CustomerDetail " + "c where c.customerEmail=:usernameParam and c.customerPassword=:userPassword");
		query.setParameter("usernameParam", login.getUsername());
		query.setParameter("userPassword", login.getPassword());

		if (query != null) {
			list = query.list();
			logger.info("customer "+login.getUsername()+" login successfull");
		}
		return list;
	}

	public int doAddCategory(String name) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		CategoryDetail categoryDetail = new CategoryDetail(name);
		session.save(categoryDetail);
		logger.info("category Added successfully"+categoryDetail.getCategoryName());
		return categoryDetail.getCategoryId();
	}

	public List<CategoryDetail> getAllCategory() throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		List<CategoryDetail> list=null;
		Query query = session.createQuery("from CategoryDetail");
		list=query.list();
		System.out.println(list + "list");
		logger.info("Got the list of all the categories"+list);
		return list;

	}

	public int doAddProduct(ProductDetail productDetail) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		CategoryDetail categoryDetail = session.get(CategoryDetail.class,
				productDetail.getCategoryId());
		productDetail.setCategory(categoryDetail.getCategoryId());
		session.save(productDetail);
		return productDetail.getProductId();
	}

	public int doDeleteCategory(CategoryDetail categoryDetail) throws ShoppingException {
		List<ProductDetail> listP=null;
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println(categoryDetail.getCategoryId()+"hhh&&&&&&&&&&");
		Query queryP = session.createQuery("from ProductDetail where categoryId= :categoryId");
		queryP.setParameter("categoryId", categoryDetail.getCategoryId());
		listP=queryP.list();
		for (ProductDetail productDetail : listP) {
			Query query1 = session.createQuery("delete OrderDetail where productId = :categoryId");
			query1.setParameter("categoryId", productDetail.getProductId());
			int result1 = query1.executeUpdate();
			Query query2 = session.createQuery("delete ShoppingCart where productId = :categoryId");
			query2.setParameter("categoryId", productDetail.getProductId());
			int result2 = query2.executeUpdate();
		}
		Query query = session.createQuery("delete ProductDetail where categoryId= :categoryId");
		query.setParameter("categoryId", categoryDetail.getCategoryId());
		query.executeUpdate();
		session.delete(categoryDetail);
		return 1;
	}

	public int doDeleteProduct(int productId) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("delete OrderDetail where productId = :categoryId");
		query1.setParameter("categoryId", productId);
		int result1 = query1.executeUpdate();
		Query query2 = session.createQuery("delete ShoppingCart where productId = :categoryId");
		query2.setParameter("categoryId", productId);
		int result2 = query2.executeUpdate();
		Query query = session.createQuery("delete ProductDetail where productId = :categoryId");
		query.setParameter("categoryId", productId);
		int result = query.executeUpdate();
		return 1;
	}

	public List<ProductDetail> doListProduct(CategoryDetail categoryDetail) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductDetail> list = null;
		Query query = session.createQuery("from ProductDetail p where categoryId = :categoryId");
		query.setParameter("categoryId", categoryDetail.getCategoryId());
		list = query.list();
		return list;
	}

	public List<ProductDetail> doViewProducts() throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductDetail> list = null;
		Query query = session.createQuery("from ProductDetail p");
		list = query.list();
		return list;
	}

	public List<ProductDetail> doSearch(String name) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		List<ProductDetail> list = null;
		List<CategoryDetail> list1 = null;
		Query query1 = session.createQuery("from CategoryDetail c where c.categoryName=:name");
		query1.setParameter("name", name);
		list1 = query1.list();
		if (list1 != null) {
			for (CategoryDetail l : list1) {
				CategoryDetail categoryDetail = new CategoryDetail(l.getCategoryId(),l.getCategoryName());
				System.out.println(categoryDetail);
				Query query = session.createQuery("from ProductDetail p where p.categoryId=:uname");
				query.setParameter("uname",l.getCategoryId());
				list = query.list();
			}
		} else {
			list = null;
		}
		return list;
	}

	public int doAddToCart(ShoppingCart cart) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(cart);
		return cart.getCartId();

	}

	public List<ShoppingCart> viewCart(int customerId) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		List<ShoppingCart> listS = new ArrayList<ShoppingCart>();
		List<ProductDetail> list = new ArrayList<ProductDetail>();
		List<ShoppingCart> listP = new ArrayList<ShoppingCart>();
		String hql = "from ShoppingCart S where S.customerId=" + customerId;
		Query query = session.createQuery(hql);
		listS = query.list();
		for (ShoppingCart s : listS) {
			Query query1 = session.createQuery("from ProductDetail where productId=:product");
			query1.setParameter("product", s.getProduct().getProductId());
			list = query1.list();
			for (ProductDetail p : list) {
				ProductDetail product = new ProductDetail(p.getProductId(), p.getProductName(), p.getProductImage(),
						p.getProductPrice(), p.getProductDescription(), p.getProductStock(), p.getCategoryId());
				ShoppingCart shoppingCart = new ShoppingCart(s.getCartId(), product, customerId, s.getCartDate());
				listP.add(shoppingCart);
			}
		}
		return listP;

	}

	public int doDeleteCartItem(int cartId) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		ShoppingCart cart = new ShoppingCart();
		cart.setCartId(cartId);
		session.delete(cart);
		return 1;
	}

	public int doPayment(CardDetail cardDetail) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(cardDetail);
		return 1;
	}

	public int doOrder(OrderDetail details) throws ShoppingException {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(details);
		return 1;
	}

}
