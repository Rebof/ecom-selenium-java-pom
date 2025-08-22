package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ecom.common.CommonComponents;

public class ProductDetailsPage extends CommonComponents{

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	private By nameLocator = By.cssSelector(".rtl-text h2");
	private By priceLocator = By.cssSelector(".rtl-text h3");
	
	
	
	
	public int getProductPriceFromDetails() {
	    waitForVisibility(priceLocator, 5); 
	    String priceText = driver.findElement(priceLocator).getText().trim();

	    if (priceText.isEmpty()) {
	        throw new RuntimeException("Price text not found on Product Details page!");
	    }

	    String digits = priceText.replaceAll("[^0-9]", "");
	    return Integer.parseInt(digits);
	}

	
	public String getProductNameFromDetails() {
	    waitForVisibility(nameLocator, 5); 
	    String nameText = driver.findElement(nameLocator).getText().trim(); 

	    if (nameText.isEmpty()) {
	        throw new RuntimeException("Name text not found on Product Details page!");
	    }

	    return nameText;
	}


			
	
	

}
