package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ecom.common.CommonComponents;

public class ForgotPassword extends CommonComponents {

    WebDriver driver;

    public ForgotPassword(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By emailField = By.cssSelector("input[formcontrolname='userEmail']");
    private By newPasswordField = By.id("userPassword");
    private By confirmPasswordField = By.id("confirmPassword");
    private By submitBtn = By.cssSelector("button[type='submit']");
    private By loginLink = By.cssSelector("a[href*='auth/login']");

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterNewPassword(String password) {
        driver.findElement(newPasswordField).sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    // convenience method to reset password in one go
    public void resetPassword(String email, String newPass) {
        enterEmail(email);
        enterNewPassword(newPass);
        enterConfirmPassword(newPass);
        clickSubmit();
    }
}
