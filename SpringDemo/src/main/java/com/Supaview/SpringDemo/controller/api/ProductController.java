package com.Supaview.SpringDemo.controller.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Supaview.SpringDemo.controller.request.ProductRequest;
import com.Supaview.SpringDemo.controller.request.UserRequest;
import com.Supaview.SpringDemo.exception.ValidationException;
import com.Supaview.SpringDemo.model.Product;
import com.Supaview.SpringDemo.model.User;
import com.Supaview.SpringDemo.service.ProductService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("products")
	public String getAllProducts(Model model) {
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);
		return "Products";
	}

	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

	@GetMapping("/editProduct/{id}")
	public String editProduct(@PathVariable long id, Model model) {
		Product data = productService.getProduct(id);

		model.addAttribute("product", data);

		return "editPage";
	}

	@PostMapping("/editProduct/{id}")
	public String editProduct(@Valid ProductRequest productRequest, BindingResult bindingResult,
			@PathVariable long id) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(field -> {
				throw new ValidationException(field.getField() + " : " + field.getDefaultMessage());
			});
		}

		productService.editProduct(productRequest, id);
		return "redirect:/products";
	}

	@GetMapping("/addproduct")
	public String addProduct() {
		return "addproduct";
	}

	@PostMapping("/addproduct")
	public String addProduct(@Valid ProductRequest productRequest, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().forEach(field -> {
				throw new ValidationException(field.getField() + " : " + field.getDefaultMessage());
			});
		}

		productService.addProduct(productRequest);

		return "redirect:/products";
	}
}
