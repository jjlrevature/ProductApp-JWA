package com.training.jwa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.jwa.model.Product;
import com.training.jwa.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository pRepo;

	@Override
	public String saveProduct(Product product) {
		if(product.getPrice() < 0 || product.getQuantityOnHand() < 0)
			return "Product price or qoh cannot be negative";
		else {
			pRepo.save(product);
			return "product did save";
		}
	}

	@Override
	public String updateProduct(Product product) {
		if(product.getPrice() < 0 || product.getQuantityOnHand() < 0)
			return "Product proce of QoH cannot be negative";
		else {
			pRepo.save(product);
			return "Product updated succesfully";
		}
	}

	@Override
	public String deleteProduct(int product_id) {
		pRepo.deleteById(product_id);
		return "Product with id: " + product_id + " was deleted";
	}

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) pRepo.findAll();
	}
	
	@Override
	public Product getProductById(int product_id) {
		Optional<Product> product = pRepo.findById(product_id);
		return product.get();
	}

	@Override
	public boolean ifProductExists(int product_id) {
		Optional<Product> product = pRepo.findById(product_id);
		return product.isPresent();
	}

	@Override
	public List<Product> getProductsByName(String productName) {
		// TODO Auto-generated method stub
		return pRepo.findByProductName(productName);
	}

	@Override
	public List<Product> getProductsByPrice(int price) {
		// TODO Auto-generated method stub
		return pRepo.findByPrice(price);
	}

	@Override
	public List<Product> getProductsByRange(int minPrice, int maxPrice) {
		// TODO Auto-generated method stub
		return pRepo.findByPriceBetween(minPrice, maxPrice);
	}

	@Override
	public List<Product> getByQuantityOnHandBetween(int minnum, int maxnum) {
		// TODO Auto-generated method stub
		return pRepo.findByQuantityOnHandBetween(minnum, maxnum);
	}

	@Override
	public List<Product> getByProductNameAndPrice(String productName, int price) {
		// TODO Auto-generated method stub
		return pRepo.findByProductNameAndPrice(productName, price);
	}
	
	

	

}
