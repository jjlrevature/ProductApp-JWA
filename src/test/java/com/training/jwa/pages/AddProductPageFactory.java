package com.training.jwa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductPageFactory {

	WebDriver driver;
	
	@FindBy(xpath = "/html/body/app-root/app-product-add/div/form/fieldset/div[1]/input")
	WebElement txt_product_id;
	
	@FindBy(xpath = "/html/body/app-root/app-product-add/div/form/fieldset/div[2]/input")
	WebElement txt_productName;
	
	@FindBy(xpath = "/html/body/app-root/app-product-add/div/form/fieldset/div[3]/input")
	WebElement txt_quantityOnHand;
	
	@FindBy(xpath = "/html/body/app-root/app-product-add/div/form/fieldset/div[4]/input")
	WebElement txt_price;
	
	@FindBy(xpath = "/html/body/app-root/app-product-add/div/form/button")
	WebElement submitBtn;
	
	public AddProductPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterProductId(int product_id) {
		txt_product_id.sendKeys(String.valueOf(product_id));
	}
	
	public void enterProductName(String productName) {
		txt_productName.sendKeys(productName);
	}
	
	public void enterQuantityOnHand(int quantityOnHand) {
		txt_quantityOnHand.sendKeys(String.valueOf(quantityOnHand));
	}
	
	public void enterPrice(int price) {
		txt_price.sendKeys(String.valueOf(price));
	}
	
	public void clickSubmitBtn() {
		submitBtn.click();
	}
}
