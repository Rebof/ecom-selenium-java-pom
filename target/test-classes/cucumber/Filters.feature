@Filters
Feature: Product Filters

  Background:
	Given The user lands on the login page	
	And the user logs in with email "validUser@example.com" and password "ValidPassword123"
	
	
  @search
  Scenario: Search product by name
    When I search for "ZARA COAT 3"
    Then I should see "ZARA COAT 3" in the results

  @price
  Scenario: Filter products by price range
    When I set the price range from "20000" to "40000"
    Then I should see the following products:
      | ZARA COAT 3     |
      | ADIDAS ORIGINAL |

  @fashion
  Scenario: Filter by Fashion category
    When I select the Fashion category
    Then I should see the following products:
      | ZARA COAT 3     |
      | ADIDAS ORIGINAL |

  @electronics
  Scenario: Filter by Electronics category
    When I select the Electronics category
    Then I should see "IPHONE 13 PRO" in the results

  @household
  Scenario: Filter by Household category
    When I select the Household category
    Then a toast message should be visible

  @shirts
  Scenario: Filter by Shirts sub-category
    When I select the Shirts sub-category
    Then I should see the following products:
      | ZARA COAT 3     |
      | ADIDAS ORIGINAL |

  @tshirts
  Scenario: Filter by T-Shirts sub-category
    When I select the T-Shirts sub-category
    Then a toast message should be visible

  @shoes
  Scenario: Filter by Shoes sub-category
    When I select the Shoes sub-category
    Then a toast message should be visible

  @mobiles
  Scenario: Filter by Mobiles sub-category
    When I select the Mobiles sub-category
    Then I should see "IPHONE 13 PRO" in the results

  @laptops
  Scenario: Filter by Laptops sub-category
    When I select the Laptops sub-category
    Then a toast message should be visible
