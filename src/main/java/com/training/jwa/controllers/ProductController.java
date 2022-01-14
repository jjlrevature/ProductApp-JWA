package com.training.jwa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.jwa.model.Product;
import com.training.jwa.services.ProductService;

/**
 * @author Jesse
 * ResponseEntity is an extension of HttpEntity that adds a status code
 */
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	ProductService pServ;
	
	
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required=false)String productName) {
		
		ResponseEntity<List<Product>> re = null;
		List<Product> productList = new ArrayList<Product>();
		
		if(productName == null) {
			productList = pServ.getAllProduct();
		} else {
			productList = pServ.getProductsByName(productName);
		}
		
		if(productList.size() == 0) {
			re = new ResponseEntity<List<Product>>(productList,HttpStatus.NO_CONTENT); // 204
		} else {
			re = new ResponseEntity<List<Product>>(productList,HttpStatus.OK); // 200
		}
		
		return re;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
		ResponseEntity<Product> re = null;
		Product product = null;
		
		if(!pServ.ifProductExists(id)) {
			//failed
			re = new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND); // 404
		} else {
			//success
			product = pServ.getProductById(id);
			re = new ResponseEntity<Product>(product,HttpStatus.OK); // 200
		}
		
		return re;
	}
	
	@PostMapping
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		
		ResponseEntity<String> re = null;
		String result=null;
		
		if(pServ.ifProductExists(product.getProduct_id())) {
			//failed
			re = new ResponseEntity<String>("Product already exists",HttpStatus.CONFLICT); // 409
		} else {
			//success
			result = pServ.saveProduct(product);
			if(result.equals("product did save")) {
				re = new ResponseEntity<String>(result,HttpStatus.CREATED); // 201
			} else {
				re = new ResponseEntity<String>(result,HttpStatus.NOT_ACCEPTABLE); // 406
			}
		}	
		
		return re;		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {

		ResponseEntity<String> re = null;
		String result=null;
		
		if(!pServ.ifProductExists(id)) {
			//failed
			re = new ResponseEntity<String>("Product was not found",HttpStatus.NOT_FOUND); // 404
		} else {
			//success
			result = pServ.deleteProduct(id);
			if(result.equals("Product with id: " + id + " was deleted")) {
				re = new ResponseEntity<String>(result,HttpStatus.OK); // 200
			} else {
				re = new ResponseEntity<String>(result,HttpStatus.NOT_ACCEPTABLE); // 406
			}
		}	
		
		return re;
	}
	
	@PutMapping
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		
		ResponseEntity<String> re = null;
		String result=null;
		
		if(!pServ.ifProductExists(product.getProduct_id())) {
			//failed
			re = new ResponseEntity<String>("No product to update",HttpStatus.NO_CONTENT); // 204
		} else {
			//success
			result = pServ.updateProduct(product);
			if(result.equals("Product updated succesfully")) {
				re = new ResponseEntity<String>(result,HttpStatus.OK); // 200
			} else {
				re = new ResponseEntity<String>(result,HttpStatus.NOT_ACCEPTABLE); // 406
			}
		}	
		
		return re;
	}
}
