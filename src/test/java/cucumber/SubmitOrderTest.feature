
@tag
Feature: Submit order flow
  As a customer
  I want to place an order
  So that I can get confirmation of purchase
	
	
  Background:
	Given The user lands on the login page	

  @E2E	
  Scenario Outline: Place an order successfully
  
    Given the user logs in with email "<email>" and password "<password>"
    When the user adds product "<productName>" to the cart
    And proceeds to checkout and adds the "<countryName>" as well
    Then the order should be confirmed with message "THANKYOU FOR THE ORDER."

    Examples:
      | email                       | password     | productName | countryName |
      | rebofkatwal10@gmail.com     | Pass@123     | ZARA COAT 3 | Nepal       |   
