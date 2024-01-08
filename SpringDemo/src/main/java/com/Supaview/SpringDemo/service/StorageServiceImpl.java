package com.Supaview.SpringDemo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Supaview.SpringDemo.exception.StorageException;

@Service
public class StorageServiceImpl implements StorageService {

	@Value("${app.upload.path:Images}")
	String path;

	Path rootlocation;

	@Override
	public void init() {
		this.rootlocation = Paths.get(path);
		try {
			Files.createDirectories(rootlocation);
		} catch (IOException e) {
			throw new StorageException("Could't not init : " + e);
		}

	}

	@Override
	public String Store(MultipartFile file) {

		if (file == null) {

			return null;
		}

		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (filename.contains("..")) {
				throw new StorageException("path outside current directory ");
			}
			filename = UUID.randomUUID() + "." + filename.substring(filename.lastIndexOf(".") + 1);

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootlocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			}
			return filename;

		} catch (IOException e) {
			throw new StorageException("Failed to store : " + e);
		}
	}

}
