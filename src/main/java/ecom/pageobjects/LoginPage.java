package ecom.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.common.CommonComponents;

public class LoginPage extends CommonComponents {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Login elements — keep private for encapsulation
    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "login")
    private WebElement loginBtn;
    
//    @FindBy(css="[class*='flyInOut']") // error wala
//	WebElement errorMessage;
    
    By errorMessage = By.cssSelector("[class*='flyInOut']");


    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginBtn.click();
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
    
    public String getErrorMessage() {
        WebElement errorElem = waitForVisibility(errorMessage, 5); 
        return errorElem.getText();
    }

}
