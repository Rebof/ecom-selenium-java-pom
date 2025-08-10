package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ecom.common.CommonComponents;

public class ConfirmationPage extends CommonComponents {

    private By confirmationMessage = By.cssSelector(".hero-primary");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns the confirmation message text displayed on the page
     */
    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }
}
