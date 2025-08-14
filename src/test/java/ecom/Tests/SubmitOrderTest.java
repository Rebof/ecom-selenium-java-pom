package ecom.Tests;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.CartPage;
import ecom.pageobjects.CheckOutPage;
import ecom.pageobjects.ConfirmationPage;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.ProductCataloguePage;
import ecom.data.DataProviders;

public class SubmitOrderTest extends BaseTest {
    String countryName = "Nepal";
    String confirmationMsg = "THANKYOU FOR THE ORDER.";

    private static final Logger logger = LogManager.getLogger(SubmitOrderTest.class);

    @Test(
        dataProvider = "getData", 
        dataProviderClass = DataProviders.class, 
        groups = {"e2e"}
    )
    public void submitOrder(HashMap<String, String> input) {
        String email = input.get("email");
        String password = input.get("password");

        String maskedPassword = password.replaceAll(".", "*");

        logger.info("Starting order submission");
        logger.info("Login credentials -> Email: {}, Password: {}", email, maskedPassword);

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication(email, password);
        logger.debug("Login successful for: {}", email);

        ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);
        productCatalogue.addProductToCart(input.get("productName"));
        logger.info("Product added to cart: {}", input.get("productName"));

        productCatalogue.goToCartFromAnywhere();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductInCart(input.get("productName")));
        logger.info("Verified product in cart: {}", input.get("productName"));

        cartPage.proceedToCheckout();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.enterShippingDetailsAndPlaceOrder(countryName);
        logger.info("Order placed for: {}", input.get("productName"));

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getConfirmationMessage(), confirmationMsg);
        logger.info("Order confirmed with message: {}", confirmationMsg);
    }
}
