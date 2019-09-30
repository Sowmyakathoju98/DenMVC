package com.virtusa.denorm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtusa.denorm.entity.CategoryDetail;
import com.virtusa.denorm.entity.ProductDetail;
import com.virtusa.denorm.exception.ShoppingException;
import com.virtusa.denorm.service.ServiceInterface;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	private ServiceInterface service;

	@Autowired
	@Qualifier(value = "service")
	public void setService(ServiceInterface service) {
		this.service = service;
	}

	@RequestMapping("/AdminController")
	public String adminHome() {
		return "AdminHome";
	}

	@RequestMapping(value = "/LogOutControllerAdmin", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		return "redirect:/HomePageController";

	}

	@RequestMapping(value = "/AddCategoryController", method = RequestMethod.GET)
	public String addCategoryGet(Model model) throws ShoppingException {
		List<CategoryDetail> list;
		list = this.service.getAllCategory();
		model.addAttribute("CategoryDetail", list);
		return "AddCategory";

	}

	@RequestMapping(value = "/AddCategoryController", method = RequestMethod.POST)
	public String addCategoryPost(@RequestParam("categoryName") String name, Model model) {
		List<CategoryDetail> list = null;
		try {
			this.service.doAddCategory(name);
			list = this.service.getAllCategory();
		} catch (ShoppingException e) {
			e.printStackTrace();
		}
		model.addAttribute("CategoryDetail", list);
		return "AddCategory";

	}

	@RequestMapping(value = "/AddProductController", method = RequestMethod.GET)
	public String addProductGet(Model model) throws ShoppingException {
		List<CategoryDetail> l = service.getAllCategory();
		Map<Integer, String> cat = new HashMap<Integer, String>();
		for (CategoryDetail categoryDetail : l) {
			cat.put(categoryDetail.getCategoryId(), categoryDetail.getCategoryName());
		}
		model.addAttribute("productDetail", new ProductDetail());
		model.addAttribute("cat", cat);
		return "AddProduct";
	}

	@RequestMapping(value = "/AddProductController", method = RequestMethod.POST)
	public String addProductPost(@Valid @ModelAttribute("productDetail") ProductDetail productDetail,
			@RequestParam("categoryId") int categoryId,Model model) {
		if(productDetail.getProductName().isEmpty()) {
			model.addAttribute("msg","please fill all the details");
			return "redirect:/Admin/AddProductController";
		}
		try {
			CategoryDetail categoryDetail = new CategoryDetail();
			categoryDetail.setCategoryId(categoryId);
			productDetail.setCategory(categoryDetail.getCategoryId());
			service.doAddProduct(productDetail);
		} catch (ShoppingException e) {
			e.printStackTrace();
		}
		return "redirect:/Admin/AddProductController";
	}

	@RequestMapping(value = "/DeleteProductController", method = RequestMethod.GET)
	public String deleteProductGet1(Model model) {
		List<ProductDetail> list;

		List<CategoryDetail> list1;
		try {
			list1 = this.service.getAllCategory();
			model.addAttribute("CategoryDetail", list1);
		} catch (ShoppingException e) {
			e.printStackTrace();
		}

		return "DeleteProduct";
	}

	@RequestMapping(value = "/DeleteProductController/{id}/{id1}", method = RequestMethod.GET)
	public String deleteProductGet(@PathVariable("id") int productId, @PathVariable("id1") int categoryId,
			Model model) {
		List<ProductDetail> list;
		System.out.println(productId + "???????????????????????");
		if (productId == 0) {
			List<CategoryDetail> list1;
			try {
				list1 = this.service.getAllCategory();
				model.addAttribute("CategoryDetail", list1);
			} catch (ShoppingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.service.doDeleteProduct(productId);
				CategoryDetail categoryDetail = new CategoryDetail(categoryId);
				list = this.service.doListProduct(categoryDetail);
				List<CategoryDetail> list1 = this.service.getAllCategory();
				model.addAttribute("CategoryDetail", list1);
				model.addAttribute("ProductDetail", list);
				// redirectAttributes.addFlashAttribute("productId", productId);
				// redirectAttributes.addFlashAttribute("categoryId", categoryId);
			} catch (ShoppingException e) {
				e.printStackTrace();
			}
		}
		return "DeleteProduct";
	}

	@RequestMapping(value = "/DeleteProductController", method = RequestMethod.POST)
	public String deleteProductPost(HttpServletRequest request, Model model) {
		String id = request.getParameter("productCategory");
		int categoryId = Integer.parseInt(id);
		List<ProductDetail> list;
		List<CategoryDetail> list1;
		CategoryDetail categoryDetail = new CategoryDetail(categoryId);
		try {
			list = this.service.doListProduct(categoryDetail);
			list1 = this.service.getAllCategory();
			model.addAttribute("CategoryDetail", list1);
			model.addAttribute("ProductDetail", list);
		} catch (ShoppingException e) {
			e.printStackTrace();
		}
		return "DeleteProduct";
	}

	@RequestMapping(value = "/DeleteCategoryController", method = RequestMethod.GET)
	public String deleteCategoryGet(HttpServletRequest request, Model model) {
		List<CategoryDetail> list;
		try {
			list = service.getAllCategory();
			model.addAttribute("CategoryDetail", list);
		} catch (ShoppingException e) {
			e.printStackTrace();
		}
		return "DeleteCategory";
	}

	@RequestMapping(value = "/DeleteCategoryController", method = RequestMethod.POST)
	public String deleteCategoryPost(@RequestParam("categoryId") int categoryId, Model model) {
		System.out.println(categoryId);
		CategoryDetail categoryDetail = new CategoryDetail(categoryId);
		try {
			this.service.doDeleteCategory(categoryDetail);
		} catch (ShoppingException e) {
			e.printStackTrace();
		}
		return "redirect:/Admin/DeleteCategoryController";
	}
}
