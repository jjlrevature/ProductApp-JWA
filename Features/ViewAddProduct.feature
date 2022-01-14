Feature: feature to add product to database End to End

  Scenario Outline: a User should be able to navigate to the add product page and add a product
    Given that a user is on the landing page
    When a user clicks on the add product link
    And enters a <product_id> <productName> <quantityOnhand> <price>
    Then the user should be navigated to the product list page.

    Examples: |
      | product_id | productName | quantityOnhand | price |
      |         10 | "MrglMrgl"  |             98 |    67 |
