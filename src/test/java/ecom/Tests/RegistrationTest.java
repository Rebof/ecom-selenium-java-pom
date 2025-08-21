package ecom.Tests;

import java.util.HashMap;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.RegistrationPage;
import ecom.data.DataProviders;

public class RegistrationTest extends BaseTest {
    
    private static final Logger logger = LogManager.getLogger(RegistrationTest.class);

    @Test(
        dataProvider = "registrationData", 
        dataProviderClass = DataProviders.class,
        groups = {"validation"}
    )
    public void registrationValidationTest(HashMap<String, String> inputData) {
        String testName = inputData.get("name");
        logger.info("Starting registration validation test: {}", testName);

        LoginPage loginPage = new LoginPage(driver);
        logger.info("Navigated to login page");

        loginPage.gotoRegistrationPage();
        logger.info("Navigated to registration page");

        RegistrationPage regPage = new RegistrationPage(driver);

        String emailFromJson = inputData.getOrDefault("email", "");
        String finalEmail = emailFromJson;
        if (!emailFromJson.isEmpty() && emailFromJson.contains("@")) {
            String[] parts = emailFromJson.split("@");
            String randomNumber = String.valueOf(new Random().nextInt(100000));
            finalEmail = parts[0] + randomNumber + "@" + parts[1];
            logger.info("Generated unique email: {}", finalEmail);
        }

        regPage.enterFirstName(inputData.getOrDefault("firstName", ""));
        regPage.enterLastName(inputData.getOrDefault("lastName", ""));
        regPage.enterEmail(finalEmail);
        regPage.enterMobile(inputData.getOrDefault("mobile", ""));
        regPage.enterPassword(inputData.getOrDefault("password", ""));
        regPage.enterConfirmPassword(inputData.getOrDefault("confirmPassword", ""));
        
        if (inputData.containsKey("occupationValue")) {
            regPage.selectOccupationByValue(inputData.get("occupationValue"));
        }
        
        if ("true".equalsIgnoreCase(inputData.get("checkbox"))) {
            regPage.clickCheckBox();
        }

        regPage.clickRegister();

        String expectedErrorXpath = inputData.get("expectedError");
        boolean isErrorDisplayed = driver.findElement(By.xpath(expectedErrorXpath)).isDisplayed();

        Assert.assertTrue(
            isErrorDisplayed, 
            "Expected error message not displayed for test case: " + testName
        );

        logger.info("Validation successful for test: {}", testName);
    }
}
