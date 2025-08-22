package ecom.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.ProductCataloguePage;
import ecom.pageobjects.ProductDetailsPage;

public class ProductDetailsTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(ProductDetailsTest.class);
    String productName = "ZARA COAT 3";
    @Test
    public void verifyProductPriceConsistency() {
        logger.info("Starting Product Price Consistency Test");
        
        // Login
        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("validUser@example.com", "ValidPassword123");
        logger.debug("Login successful");

        // Product Catalogue
        ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);
        List<WebElement> products = productCatalogue.getProductList();


        int cataloguePrice = productCatalogue.goToProductDetails(productName);
        logger.info("Price from Catalogue Page: " + cataloguePrice);

        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        int detailsPrice = productDetailsPage.getProductPriceFromDetails();
        String detailsName = productDetailsPage.getProductNameFromDetails();

        logger.info("Price from Details Page: " + detailsPrice);

        Assert.assertEquals(detailsName, productName,
                "Catalogue and Details page names do not match!");
        
        Assert.assertEquals(detailsPrice, cataloguePrice,
                "Catalogue and Details page prices do not match!");

        logger.info("Price consistency test completed successfully.");
    }
}
