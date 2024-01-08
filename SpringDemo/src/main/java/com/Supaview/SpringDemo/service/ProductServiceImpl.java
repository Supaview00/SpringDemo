package com.Supaview.SpringDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Supaview.SpringDemo.controller.request.ProductRequest;
import com.Supaview.SpringDemo.exception.ProductNotFoundException;
import com.Supaview.SpringDemo.model.Product;
import com.Supaview.SpringDemo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private StorageService storageService;
	private ProductRepository productRepository;

	public ProductServiceImpl(StorageService storageService, ProductRepository productRepository) {
		this.storageService = storageService;
		this.productRepository = productRepository;

	}

	@Override
	public void deleteProduct(long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new ProductNotFoundException(id);
		}

	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		// return productRepository.findAll(Sort.by(Sort.Order.asc("id")));
	}

	@Override
	public Product getProduct(long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		throw new ProductNotFoundException(id);

	}

	@Override
	public Product editProduct(ProductRequest productRequest, long id) {
		Optional<Product> product = productRepository.findById(id);

		if (product.isPresent()) {

			String image = storageService.Store(productRequest.getFile());

			Product existingProduct = product.get();

			if (image != null) {

				existingProduct.setImage(image);

			}

			existingProduct.setName(productRequest.getName());
			existingProduct.setPrice(productRequest.getPrice());
			existingProduct.setStock(productRequest.getStock());

			return productRepository.save(existingProduct);
		}
		throw new ProductNotFoundException(id);

	}

	@Override
	public Product addProduct(ProductRequest productRequest) {
		Product product = new Product();
		String image = storageService.Store(productRequest.getFile());

		product.setName(productRequest.getName());
		product.setImage(image);
		product.setPrice(productRequest.getPrice());
		product.setStock(productRequest.getStock());

		return productRepository.save(product);
	}

}
