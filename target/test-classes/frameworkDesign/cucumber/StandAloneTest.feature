@E2E_Test
Feature: StandAlone E2E test for E-Commerce

  Background: 
    Given I landed on the ECommerce Page

  @StandAloneTest
  Scenario Outline: StandAlone E2E Test
    Given I have logged in with <username> and <password>
    When I add the product <productName> to the cart
    When Checkout <productName> and submit the order
    Then Select "Ind" and "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | username                   | password  | productName |
      | nick.verma.krish@gmail.com | Mi4i@2015 | ZARA COAT 3 |
