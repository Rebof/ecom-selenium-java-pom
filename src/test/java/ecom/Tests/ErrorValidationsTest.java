package ecom.Tests;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.CartPage;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(ErrorValidationsTest.class);

    @Test(
        groups = {"loginValidation"},
        retryAnalyzer = ecom.TestComponents.Retry.class
    )
    public void loginErrorValidation() throws IOException, InterruptedException {
        logger.info("Starting login error validation test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("invalidUser@example.com", "WrongPassword123");
        String actualError = loginP.getErrorMessage();

        logger.debug("Captured error message: {}", actualError);
        Assert.assertEquals(actualError, "Incorrect email or password."); // jani jani fail for extent report debugging
        logger.info("Login error validation passed");
    }

    @Test(
        groups = {"cartValidation"}
    )
    public void productErrorValidation() throws IOException, InterruptedException {
        logger.info("Starting product error validation test");

        String expectedProduct = "ZARA COAT 3";
        String wrongProduct = "ZARA COAT 33";

        // Login
        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("validUser@example.com", "ValidPassword123");
        logger.debug("Login successful");

        // Product Catalogue
        ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);
        List<WebElement> products = productCatalogue.getProductList();
        logger.debug("Retrieved {} products from catalogue", products.size());

        // Add product to cart
        productCatalogue.addProductToCart(expectedProduct);
        logger.info("Product added to cart: {}", expectedProduct);

        // Go to cart and verify wrong product is not displayed
        productCatalogue.goToCartFromAnywhere();
        CartPage cartPage = new CartPage(driver);
        boolean match = cartPage.isProductInCart(wrongProduct);

        Assert.assertFalse(match, "Unexpected product found in cart: " + wrongProduct);
        logger.info("Verified product error validation passed");
    }
}
