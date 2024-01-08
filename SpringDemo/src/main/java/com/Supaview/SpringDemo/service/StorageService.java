package com.Supaview.SpringDemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	String Store(MultipartFile file);
}
