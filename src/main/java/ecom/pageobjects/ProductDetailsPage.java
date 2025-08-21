package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ecom.common.CommonComponents;

public class ProductDetailsPage extends CommonComponents{

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	private By nameLocator = By.cssSelector("h2");
	private By priceLocator = By.cssSelector("h3");
	
	
	
	
	public int getProductPriceFromDetails() {
	    String priceText = driver.findElement(priceLocator).getText();

	    String digits = priceText.replaceAll("[^0-9]", "");

	    return Integer.parseInt(digits);
	}
	

			
	
	

}
