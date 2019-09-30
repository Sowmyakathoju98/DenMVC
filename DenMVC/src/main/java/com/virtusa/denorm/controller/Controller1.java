package com.virtusa.denorm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.virtusa.denorm.entity.CardDetail;
import com.virtusa.denorm.entity.OrderDetail;
import com.virtusa.denorm.entity.ProductDetail;
import com.virtusa.denorm.entity.ShoppingCart;
import com.virtusa.denorm.exception.ShoppingException;
import com.virtusa.denorm.service.ServiceInterface;

@Controller
@SessionAttributes({ "cid", "name" ,"quantity","productName","productPrice","productTotalPrice"})
public class Controller1 {

	private ServiceInterface service;

	@Autowired(required = true)
	@Qualifier(value = "service")
	public void setService(ServiceInterface service) {
		this.service = service;
	}

	@RequestMapping("/SearchController")
	public String search(@RequestParam("searchText") String searchItem, Model model) throws ShoppingException {
		List<ProductDetail> list = new ArrayList<ProductDetail>();
		list = this.service.doSearch(searchItem);
		if (list != null) {
			model.addAttribute("list", list);
			return "HomePage";
		} else {
			return "Error";
		}

	}

	@RequestMapping(value = "/AddToCartController/{pid}", method = RequestMethod.GET)
	public String addToCartGet(@PathVariable("pid") int productId, Model model, HttpServletRequest request)
			throws ShoppingException {
		HttpSession session = request.getSession();
		String return1 = null;
		Object customerId = session.getAttribute("cid");
		if (customerId != null) {
			int custId = (Integer) customerId;
			final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String cartDate = sdf.format(date);
			ProductDetail product = new ProductDetail(productId);
			ShoppingCart cart = new ShoppingCart(product, custId, cartDate);
			try {
				int id = this.service.doAddToCart(cart);
				if (id != 0) {
					model.addAttribute("cartId", id);
					return1 = "redirect:/HomePageController";
				} else {
					return1 = "Error";
				}
			} catch (ShoppingException e) {
				e.printStackTrace();
			}
		} else {
			model.addAttribute("addToCart", "Please Login to add the products to cart");
			return1 = "redirect:/LoginController";
		}
		return return1;
	}

	@RequestMapping("/MyCartController")
	public String cart(HttpSession session, Model model) throws ShoppingException {
		int customerId = (Integer) session.getAttribute("cid");
		List<ShoppingCart> list = null;
		list = this.service.viewCart(customerId);
		if (list != null) {
			session.setAttribute("CartList", list);
		}
		return "ViewCart";
	}

	@RequestMapping(value = "/DeleteCartItemController/{cartId}")
	public String deletCartItem(@PathVariable("cartId") int cartId) throws ShoppingException {
		this.service.doDeleteCartItem(cartId);
		return "redirect:/MyCartController";
	}

	@RequestMapping(value = "/BuyNowController")
	public String buyNowGet(@ModelAttribute("productDetail") ProductDetail productDetail, final Model model)
			throws ShoppingException {
		model.addAttribute("product", productDetail);
		return "BuyNow";
	}

	@RequestMapping(value = "/BuyNowController/{productId}", method = RequestMethod.POST)
	public String buyNowPost(@PathVariable("productId") int productId, HttpServletRequest request,
			@ModelAttribute("productDetail") ProductDetail productDetail, final BindingResult productDetailResult,
			final Model model, final RedirectAttributes redirectAttributes) throws ShoppingException {
		HttpSession session=request.getSession();
		Object cid = session.getAttribute("cid");
		if (cid != null) {
			String productName = request.getParameter("productName");
			String productImage = request.getParameter("productImage");
			String productDescription = request.getParameter("productDescription");
			float productPrice = Float.parseFloat(request.getParameter("productPrice"));
			productDetail = new ProductDetail(productId, productName, productImage, productPrice, productDescription);
			redirectAttributes.addFlashAttribute("productDetail", productDetail);
			return "redirect:/BuyNowController";
		} else {
			model.addAttribute("Payment", "Please Login to Buy the product");
			return "redirect:/LoginController";
		}

	}

	@RequestMapping(value = "/DoBuyNowController/{productId}", method = RequestMethod.POST)
	public String doBuy(@PathVariable("productId") int productId, Model model, HttpServletRequest request)
			throws ShoppingException {
		HttpSession session = request.getSession();
		int cid = (Integer) session.getAttribute("cid");
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String productImage = request.getParameter("productImage");
		int quantity1 = Integer.parseInt(request.getParameter("quantity"));
		float quantity = Float.parseFloat(request.getParameter("quantity"));
		float productPrice = Float.parseFloat(request.getParameter("productPrice"));
		float productTotalPrice = productPrice * quantity;
		model.addAttribute("quantity", quantity);
		model.addAttribute("productName", productName);
		model.addAttribute("productPrice", productPrice);
		model.addAttribute("productTotalPrice", productTotalPrice);
		ProductDetail productDetail = new ProductDetail(productId, productName, productImage, productPrice,
				productDescription);
		OrderDetail orderDetail = new OrderDetail(cid, productDetail, quantity1, productTotalPrice, 1);
		try {
			this.service.doOrder(orderDetail);
		} catch (ShoppingException e) {
			e.printStackTrace();
		}
		return "redirect:/PaymentController";
	}

	@RequestMapping(value = "/PaymentController", method = RequestMethod.GET)
	public String doPaymentget(Model model) throws ShoppingException {
		model.addAttribute("cardDetail", new CardDetail());
		return "Payment";
	}

	@RequestMapping(value = "/PaymentController", method = RequestMethod.POST)
	public String doPayment(@Valid@ModelAttribute("cardDetail") CardDetail cardDetail,BindingResult result) throws ShoppingException {
		if(result.hasErrors()) {
			System.out.println("errors");
			return "Payment";
		}
		this.service.doPayment(cardDetail);
		return "redirect:/BillingPage";
	}

	@RequestMapping(value = "/BillingPage", method = RequestMethod.GET)
	public String doBill(HttpServletRequest request) throws ShoppingException {
		return "BillingPage";
	}

}
