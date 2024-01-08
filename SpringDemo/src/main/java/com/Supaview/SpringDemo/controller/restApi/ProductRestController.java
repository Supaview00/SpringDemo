package com.Supaview.SpringDemo.controller.restApi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Supaview.SpringDemo.controller.request.ProductRequest;
import com.Supaview.SpringDemo.model.Product;
import com.Supaview.SpringDemo.service.ProductService;
import com.Supaview.SpringDemo.service.ProductServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	private ProductService productService;

	public ProductRestController(ProductService productService) {
		this.productService = productService;

	}

	@GetMapping
	public List<Product> getallProduct() {
		return productService.getAllProduct();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable long id) {
		return productService.getProduct(id);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public Product createProduct(@Valid ProductRequest productRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(field -> {
				throw new ValidationException(field.getField() + " : " + field.getDefaultMessage());
			});
		}
		return productService.addProduct(productRequest);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/{id}")
	public Product editProduct(@Valid ProductRequest productRequest, BindingResult bindingResult,
			@PathVariable long id) {
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().stream().forEach(field -> {
				throw new ValidationException(field.getField() + " : " + field.getDefaultMessage());
			});
		}
		return productService.editProduct(productRequest, id);
	}
}
