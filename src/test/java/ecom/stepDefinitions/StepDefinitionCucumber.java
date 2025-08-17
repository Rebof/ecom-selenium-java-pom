package ecom.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.CartPage;
import ecom.pageobjects.CheckOutPage;
import ecom.pageobjects.ConfirmationPage;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.ProductCataloguePage;
import io.cucumber.java.en.*;

public class StepDefinitionCucumber extends BaseTest {

    private LoginPage loginPage;
    private ProductCataloguePage productCatalogue;
    private CartPage cartPage;
    private CheckOutPage checkOutPage;
    private ConfirmationPage confirmationPage;

    // Positive Order Flow Steps

    @Given("The user lands on the login page")
    public void the_user_lands_on_the_login_page() throws IOException {
        launchApplication(); 
    }

    @Given("the user logs in with email {string} and password {string}")
    public void the_user_logs_in_with_email_and_password(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginApplication(email, password);
        productCatalogue = new ProductCataloguePage(driver);
    }

    @When("the user adds product {string} to the cart")
    public void the_user_adds_product_to_the_cart(String productName) {
        productCatalogue.addProductToCart(productName);
        productCatalogue.goToCartFromAnywhere();

        cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart: " + productName);
    }

    @When("proceeds to checkout and adds the {string} as well")
    public void proceeds_to_checkout(String countryName) {
        cartPage.proceedToCheckout();
        checkOutPage = new CheckOutPage(driver);
        checkOutPage.enterShippingDetailsAndPlaceOrder(countryName);
    }

    @Then("the order should be confirmed with message {string}")
    public void the_order_should_be_confirmed_with_message(String expectedMsg) {
        confirmationPage = new ConfirmationPage(driver);
        String actualMsg = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(actualMsg, expectedMsg, "Confirmation message mismatch!");
        
        driver.close();
    }

    // Negative / Validation Steps

    @Then("the login error message should be {string}")
    public void the_login_error_message_should_be(String expectedError) {
        loginPage = new LoginPage(driver);
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError, "Login error message mismatch!");
        driver.close();
    }

    @Then("the product {string} should not be present in the cart")
    public void the_product_should_not_be_present_in_the_cart(String wrongProduct) {
        cartPage = new CartPage(driver);
        boolean match = cartPage.isProductInCart(wrongProduct);
        Assert.assertFalse(match, "Unexpected product found in cart: " + wrongProduct);
        driver.close();
    }

}
