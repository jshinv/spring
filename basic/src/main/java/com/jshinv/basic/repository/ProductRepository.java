package com.jshinv.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jshinv.basic.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
//	리스트 아닌 단건으로 표시 하고자 할때
//	public Product findByName(String name);
	
	public List<Product> findByName(String name);
}