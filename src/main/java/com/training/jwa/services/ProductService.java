package com.training.jwa.services;

import java.util.List;

import com.training.jwa.model.Product;

public interface ProductService {
	
	public String saveProduct(Product product);

	public String updateProduct(Product product);

	public String deleteProduct(int product_id);

	public Product getProductById(int product_id);
	
	public List<Product> getAllProduct();
	
	public List<Product> getProductsByName(String productName);
	
	public List<Product> getProductsByPrice(int price);
	
	public List<Product> getProductsByRange(int minPrice, int maxPrice);
	
	public List<Product> getByQuantityOnHandBetween(int minnum, int maxnum);
	
	public List<Product> getByProductNameAndPrice(String productName, int price);
	
	public boolean ifProductExists(int product_id);
}
