package com.training.jwa.steps;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.training.jwa.pages.AddProductPageFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddProductSteps {
	
	WebDriver driver;
	
	AddProductPageFactory addProductPage;

	@Given("that a user is on the landing page")
	public void that_a_user_is_on_the_landing_page() {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    addProductPage = new AddProductPageFactory(driver);
	    driver.navigate().to("http://localhost:4200");
	}

	@When("a user clicks on the add product link")
	public void a_user_clicks_on_the_add_product_link() {
	    driver.findElement(By.xpath("/html/body/app-root/app-product-list/button")).click();
	}

	@When("enters a {int} {string} {int} {int}")
	public void enters_a(Integer product_id, String productName, Integer quantityOnHand, Integer price) {
//		                      Using a WebDriver object to enter info and submit
//	    driver.findElement(By.xpath("/html/body/app-root/app-product-add/div/form/fieldset/div[1]/input")).sendKeys(String.valueOf(product_id));
//	    driver.findElement(By.xpath("/html/body/app-root/app-product-add/div/form/fieldset/div[2]/input")).sendKeys(productName);
//	    driver.findElement(By.xpath("/html/body/app-root/app-product-add/div/form/fieldset/div[3]/input")).sendKeys(String.valueOf(quantityOnHand));
//	    driver.findElement(By.xpath("/html/body/app-root/app-product-add/div/form/fieldset/div[4]/input")).sendKeys(String.valueOf(price));
//	    driver.findElement(By.xpath("/html/body/app-root/app-product-add/div/form/button")).click();
		
//		                      Using a Page Object Model to enter the info and submit
//		addProductPage.enterProductId(product_id);
//		addProductPage.enterProductName(productName);
//		addProductPage.enterQuantityOnHand(quantityOnHand);
//		addProductPage.enterPrice(price);
//		addProductPage.clickSubmitBtn();
	
//		                      Using JavascriptExecutor object to enter info and submit
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('productId').value="+ product_id);
		js.executeScript("document.getElementById('productName').value='"+ productName + "'");
		js.executeScript("document.getElementById('qoh').value="+ quantityOnHand);
		js.executeScript("document.getElementById('price').value="+ price);
		js.executeScript("document.getElementById('addSubmit').click()");
		
		
	}

	@Then("the user should be navigated to the product list page.")
	public void the_user_should_be_navigated_to_the_product_list_page() {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    assertTrue(driver.findElement(By.xpath("/html/body/app-root/app-product-list/p")).isDisplayed());
	    driver.close();
	    driver.quit();
	}
}
