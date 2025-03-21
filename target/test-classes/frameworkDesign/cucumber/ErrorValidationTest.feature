@E2E_Test
Feature: Error Validation Test

  Background: 
    Given I landed on the ECommerce Page

  @ErrorValidation
  Scenario Outline: Verify test fails for incorrect username/password
    Given I have logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed on the page

    Examples: 
      | username                   | password | productName  |
      | nick.verma.krish@gmail.com | Mi@2015  | ZARA COAT 33 |
