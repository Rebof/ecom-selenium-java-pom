package ecom.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ecom.common.CommonComponents;

public class ProductCataloguePage extends CommonComponents {

    // Locators
    private By productsLocator = By.cssSelector(".mb-3");
    private By productNameTag = By.cssSelector("b");
    private By addToCartButton = By.cssSelector(".card-body button:last-of-type");

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets list of products after waiting for them to be visible
     */
    public List<WebElement> getProductList() {
        waitForVisibility(productsLocator, 5);
        return driver.findElements(productsLocator);
    }

    /**
     * Finds a product by its name
     */
    public WebElement getProductByName(String productName) {
        return getProductList()
                .stream()
                .filter(product -> product.findElement(productNameTag).getText().equals(productName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a specific product to the cart
     */
    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        if (prod != null) {
            prod.findElement(addToCartButton).click();
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }
}
