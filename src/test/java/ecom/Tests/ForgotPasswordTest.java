package ecom.Tests;

import java.util.HashMap;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.ForgotPassword;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.RegistrationPage;

public class ForgotPasswordTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ForgotPasswordTest.class);

    @Test
    public void forgotPasswordFlow() {
        // Generate random email for new registration
        String randomEmail = "test" + new Random().nextInt(100000) + "@example.com";
        String initialPassword = "Password123";
        String newPassword = "Pass@123";

        HashMap<String, String> inputData = new HashMap<>();
        inputData.put("firstName", "Piccolo");
        inputData.put("lastName", "Namek");
        inputData.put("email", randomEmail); 
        inputData.put("mobile", "9803030300");
        inputData.put("password", initialPassword);
        inputData.put("confirmPassword", initialPassword);
        inputData.put("occupationValue", "3: Engineer");
        inputData.put("checkbox", "true");
        
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.gotoRegistrationPage();


        RegistrationPage regPage = new RegistrationPage(driver);
        regPage.enterFirstName(inputData.getOrDefault("firstName", ""));
        regPage.enterLastName(inputData.getOrDefault("lastName", ""));
        regPage.enterEmail(randomEmail); // use random email
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
        logger.info("Registered new user: {}", randomEmail);
        
        regPage.clickLoginButton();

        // Forgot Password
        loginPage.gotoForgetPwPage();
        ForgotPassword forgotPasswordPage = new ForgotPassword(driver);

        forgotPasswordPage.enterEmail(randomEmail);
        forgotPasswordPage.enterNewPassword(newPassword);
        forgotPasswordPage.enterConfirmPassword(newPassword);
        forgotPasswordPage.clickSubmit();
        logger.info("Password reset done for user: {}", randomEmail);

        forgotPasswordPage.clickLoginLink();

        // Login with new password
        String maskedPassword = newPassword.replaceAll(".", "*");
        logger.info("Logging in with new password -> Email: {}, Password: {}", randomEmail, maskedPassword);
        loginPage.loginApplication(randomEmail, newPassword);
        logger.debug("Login successful with new password for: {}", randomEmail);

        
    }
}
