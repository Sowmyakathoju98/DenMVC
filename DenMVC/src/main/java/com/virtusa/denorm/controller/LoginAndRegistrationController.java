package com.virtusa.denorm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtusa.denorm.entity.CustomerDetail;
import com.virtusa.denorm.entity.LoginDetail;
import com.virtusa.denorm.entity.ProductDetail;
import com.virtusa.denorm.exception.ShoppingException;
import com.virtusa.denorm.service.ServiceInterface;

@Controller
@SessionAttributes({"cid","name"})
public class LoginAndRegistrationController {
	
	
	private ServiceInterface service;
	private static final Logger logger =
	Logger.getLogger(LoginAndRegistrationController.class);

	@Autowired(required = true)
	@Qualifier(value = "service")
	public void setService(ServiceInterface service) {
		this.service = service;
	}

	@ModelAttribute
	public void addingCommonObjects(Model model1) throws ShoppingException {
		List<ProductDetail> l = this.service.doViewProducts();
		if (l != null) {
			model1.addAttribute("pList", this.service.doViewProducts());
		}
	}

	@RequestMapping(value = "/HomePageController", method = RequestMethod.GET)
	public String home() {
		logger.info("Home Page Entered");
		return "HomePage";

	}

	@RequestMapping(value = "/RegistrationController", method = RequestMethod.GET)
	public String registrationGet(Model model) {
		model.addAttribute("customer", new CustomerDetail());
		return "Registration";

	}

	@RequestMapping(value = "/RegistrationController", method = RequestMethod.POST)
	public String registrationPost(@Valid @ModelAttribute("customer") CustomerDetail customerDetail,
			BindingResult result) throws ShoppingException {
		if (result.hasErrors()) {
			return "Registration";
		}
		this.service.doRegister(customerDetail);
		return "redirect:/LoginController";

	}

	@RequestMapping(value = "/LoginController", method = RequestMethod.GET)
	public String loginGet(final Model model,@ModelAttribute("msg") String msg) {
		model.addAttribute("login", new LoginDetail());
		if(msg!=null) {
			model.addAttribute("msg",msg);
		}
		return "Login";
	}

	@RequestMapping(value = "/LoginController", method = RequestMethod.POST)
	public String loginPost(@Valid @ModelAttribute("login") LoginDetail login,
			BindingResult result,final Model model, final RedirectAttributes redirectAttributes) throws ShoppingException {
		if (login.getUsername().equalsIgnoreCase("admin@gmail.com") && login.getPassword().equals("admin")) {
			model.addAttribute("cid", "Admin");
			return "redirect:/Admin/AdminController";
		}else {
			List<CustomerDetail> customer = this.service.doLogin(login);
			if (customer.isEmpty()) {
				redirectAttributes.addAttribute("msg","Invalid Username OR Password");
				return "redirect:/LoginController";
			} else {
				model.addAttribute("cid", customer.get(0).getCustomerId());
				model.addAttribute("name", customer.get(0).getCustomerName());
				return "redirect:/HomePageController";
			}
		}
		
	}

	@RequestMapping(value = "/LogOutController", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		System.out.println("Logged Out ");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			model.addAttribute("cid", null);
			model.addAttribute("name", null);
			
		}
		return "redirect:/HomePageController";

	}

	@RequestMapping(value = "/FAQ", method = RequestMethod.GET)
	public String faq() {
		return "FAQ";

	}

	@RequestMapping(value = "/Help", method = RequestMethod.GET)
	public String help() {
		return "Help";

	}

	@RequestMapping(value = "/AboutUs", method = RequestMethod.GET)
	public String aboutUs() {
		return "AboutUs";

	}
}
