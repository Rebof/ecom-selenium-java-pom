package ecom.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecom.common.CommonComponents;


public class RegistrationPage extends CommonComponents{
    

    // Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private By loginButton = By.cssSelector("button[routerlink='/auth'].btn.btn-primary");
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By mobile = By.id("userMobile");
    private By password = By.id("userPassword");
    private By confirmPassword = By.id("confirmPassword");
    private By occupation = By.xpath("//select[@formcontrolname='occupation']");
    private By genderMale = By.xpath("//input[@type='radio' and @value='Male']");
    private By checkBox = By.xpath("//input[@type='checkbox']");
    private By registerButton = By.id("login");

    // Methods to interact with elements
    public void enterFirstName(String fName) {
        driver.findElement(firstName).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        driver.findElement(lastName).sendKeys(lName);
    }

    public void enterEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    public void enterMobile(String mob) {
        driver.findElement(mobile).sendKeys(mob);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void enterConfirmPassword(String confirmPass) {
        driver.findElement(confirmPassword).sendKeys(confirmPass);
    }
    
    // ---------------- Login Button ----------------
    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    // Select occupation by VALUE attribute (e.g. "1: Doctor", "2: Student", etc.)
    public void selectOccupationByValue(String occValue) {
        Select select = new Select(driver.findElement(occupation));
        select.selectByValue(occValue);
    }

    public void selectGenderMale() {
        driver.findElement(genderMale).click();
    }

    public void clickCheckBox() {
        driver.findElement(checkBox).click();
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }
}
