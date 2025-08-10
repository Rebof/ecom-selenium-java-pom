package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ecom.common.CommonComponents;

public class CheckOutPage extends CommonComponents {

    // Locators
    private By countryInput = By.cssSelector("[placeholder='Select Country']");
    private By countryResults = By.cssSelector(".ta-results");
    private By firstCountryOption = By.xpath("//button[contains(@class,'ta-item')]");
    private By placeOrderButton = By.cssSelector(".action__submit");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters shipping country, selects the first suggestion, and places the order
     */
    public void enterShippingDetailsAndPlaceOrder(String countryName) {
        // Type country name using Actions
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(countryInput), countryName).build().perform(); // first is the target elemetn, second is what should be sent

        // Wait for results to appear
        waitForVisibility(countryResults, 5);

        // Select first suggestion
        driver.findElement(firstCountryOption).click();

        // Click place order
        driver.findElement(placeOrderButton).click();
    }
}
