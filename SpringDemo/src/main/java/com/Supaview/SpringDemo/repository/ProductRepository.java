package com.Supaview.SpringDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Supaview.SpringDemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
