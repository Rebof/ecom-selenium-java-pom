package ecom.stepDefinitions;

import java.io.IOException;
import java.util.List;

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

    // -------------------------
    // Common / Login Steps
    // -------------------------

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

    // -------------------------
    // Order Flow Steps
    // -------------------------

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

    // -------------------------
    // Negative / Validation Steps
    // -------------------------

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

    // -------------------------
    // Filter Steps
    // -------------------------

    @When("I search for {string}")
    public void i_search_for(String productName) {
        productCatalogue.searchProduct(productName);
    }

    @Then("I should see {string} in the results")
    public void i_should_see_in_results(String productName) {
        List<String> products = productCatalogue.getVisibleProductNames();
        Assert.assertTrue(products.contains(productName), productName + " should be visible");
        driver.close();
    }

    @When("I set the price range from {string} to {string}")
    public void i_set_price_range(String min, String max) {
        productCatalogue.setPriceRange(min, max);
    }

    @Then("I should see the following products:")
    public void i_should_see_the_following_products(io.cucumber.datatable.DataTable dataTable) {
        List<String> expected = dataTable.asList();
        List<String> actual = productCatalogue.getVisibleProductNames();
        Assert.assertTrue(actual.containsAll(expected),
                "Expected: " + expected + " but got: " + actual);
        driver.close();
    }

    @When("I select the Fashion category")
    public void i_select_fashion() {
        productCatalogue.selectFashion();
    }

    @When("I select the Electronics category")
    public void i_select_electronics() {
        productCatalogue.selectElectronics();
    }

    @When("I select the Household category")
    public void i_select_household() {
        productCatalogue.selectHousehold();
    }

    @When("I select the Shirts sub-category")
    public void i_select_shirts() {
        productCatalogue.selectShirts();
    }

    @When("I select the T-Shirts sub-category")
    public void i_select_tshirts() {
        productCatalogue.selectTshirts();
    }

    @When("I select the Shoes sub-category")
    public void i_select_shoes() {
        productCatalogue.selectShoes();
    }

    @When("I select the Mobiles sub-category")
    public void i_select_mobiles() {
        productCatalogue.selectMobiles();
    }

    @When("I select the Laptops sub-category")
    public void i_select_laptops() {
        productCatalogue.selectLaptops();
    }

    @Then("a toast message should be visible")
    public void toast_should_be_visible() {
        Assert.assertTrue(productCatalogue.isToastVisible(), "Toast should be visible");
        driver.close();
    }

}
