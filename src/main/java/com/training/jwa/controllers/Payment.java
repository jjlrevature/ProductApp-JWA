package com.training.jwa.controllers;

import org.springframework.stereotype.Service;

@Service
public class Payment {

	public String pay(double amount) {
		return "Succesfully credited USD : " + amount;
	}
	
	public String payWithCustomerName(double amount, String name) {
		return "Succesfully credited USD : " + amount+ " to "+ name;
	}
}
