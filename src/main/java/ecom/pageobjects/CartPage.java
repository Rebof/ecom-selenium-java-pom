package ecom.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ecom.common.CommonComponents;

public class CartPage extends CommonComponents {

    private By cartProductTitles = By.cssSelector(".cartSection h3");
    private By checkoutButton = By.cssSelector(".totalRow button");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns true if the given product is found in the cart
     */
    public boolean isProductInCart(String productName) {
        List<WebElement> cartProducts = driver.findElements(cartProductTitles);
        return cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }
    
    
    /**
     * Clicks the checkout button
     */
    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
