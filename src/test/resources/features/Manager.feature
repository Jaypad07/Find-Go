Feature: Manager is able to register, login, and manipulate Store data
  Scenario: Manager wants to add a store
    Given I am on the registration page
    When I enter valid registration details (username, email, password)
    Then I should be successfully registered