package com.Supaview.SpringDemo.controller.request;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

	@NotEmpty(message = "name must not empty")
	@Size(max = 55, min = 3)
	private String name;

	private MultipartFile file;

	private int price;

	private int stock;
}
