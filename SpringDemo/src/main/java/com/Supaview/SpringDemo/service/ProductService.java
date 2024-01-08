package com.Supaview.SpringDemo.service;

import java.util.List;

import com.Supaview.SpringDemo.controller.request.ProductRequest;
import com.Supaview.SpringDemo.model.Product;

public interface ProductService {

	void deleteProduct(long id);

	List<Product> getAllProduct();

	Product getProduct(long id);

	Product editProduct(ProductRequest productRequest, long id);

	Product addProduct(ProductRequest productRequest);
}
