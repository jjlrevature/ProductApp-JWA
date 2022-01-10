package com.training.jwa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.jwa.model.Product;


/**
 * @author Jesse
 * Spring Data Jpa will generate the queries/methods
 * go to https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
 * to see keywords and how to use
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	
	public List<Product> findByProductName(String productName);
	
	public List<Product> findByPrice(int price);
	
	public List<Product> findByPriceBetween(int minnum, int maxnum);
	
	public List<Product> findByQuantityOnHandBetween(int minnum, int maxnum);
	
	public List<Product> findByProductNameAndPrice(String productName, int price);

}
