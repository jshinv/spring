package com.jshinv.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jshinv.basic.model.Product;
import com.jshinv.basic.repository.ProductRepository;


@RestController
public class JpaController {
	@Autowired
	ProductRepository productRepository;
	
//	@GetMapping("/jpa/product2")
//	public Product product2(String name) {
//		Product list = productRepository.findByName(name);
//		return list;
//	}

	@GetMapping("/jpa/product3")
	public List<Product> product3(String name) {
		List<Product> list = productRepository.findByName(name);
		return list;
	}
	
	@GetMapping("/jpa/product")
	public List<Product> product() {
		List<Product> list = productRepository.findAll();
		return list;
	}

	@PostMapping("/jpa/product")
	public String productPost(@ModelAttribute Product product) {
		productRepository.save(product);
		return "redirect:/jpa/product";
	}
}