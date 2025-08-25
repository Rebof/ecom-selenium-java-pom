package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ecom.common.CommonComponents;

public class OrderPage extends CommonComponents {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Dynamic row locator by product name
    private String rowByProductName = "//table[contains(@class,'table')]//tbody/tr[@class='ng-star-inserted' and td[2][normalize-space()='%s']]";

    // Toast message locator
    private By toastMsg = By.cssSelector("#toast-container"); 

    // Delete a product by name
    public void deleteProductByName(String productName) {
        WebElement row = driver.findElement(By.xpath(String.format(rowByProductName, productName)));
        row.findElement(By.cssSelector(".btn.btn-danger")).click();
    }

    // Get toast message text
    public String getToastMessage() {
        return driver.findElement(toastMsg).getText();
    }
    

    public By getToastLocator() {
        return toastMsg;
    }
}
