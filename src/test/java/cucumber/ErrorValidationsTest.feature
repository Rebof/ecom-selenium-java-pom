@ErrorValidations
Feature: Error validations
  To ensure the application handles errors correctly
  As a customer
  I want to validate error messages and cart mismatches

  Background:
    Given The user lands on the login page

  @LoginValidation
  Scenario: Login with invalid credentials shows error message
    When the user logs in with email "invalidUser@example.com" and password "WrongPassword123"
    Then the login error message should be "Incorrect email or password."

  @CartValidation
  Scenario: Product mismatch validation in cart
    Given the user logs in with email "validUser@example.com" and password "ValidPassword123"
    When the user adds product "ZARA COAT 3" to the cart
    And proceeds to checkout and adds the "Nepal" as well
    Then the product "ZARA COAT 33" should not be present in the cart
