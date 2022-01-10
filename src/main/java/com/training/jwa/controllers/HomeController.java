package com.training.jwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jesse
 * Autowired is telling spring boot to choose a bean that is already
 * created and in the IoC container and use that.
 */
@RestController
public class HomeController {
	
	
	
	
	
	@Autowired
	Payment payment;

	@RequestMapping("home")
	public String home() {
		return "Hello World";
	}
	
	@RequestMapping("pay")
	public String pay() {
		return payment.pay(11500);
	}
	
	@RequestMapping("pay/{amount}/{name}")
	public String payAmount(@PathVariable("amount") double amount, @PathVariable("name") String name) {
		return payment.payWithCustomerName(amount, name);
	}
}
