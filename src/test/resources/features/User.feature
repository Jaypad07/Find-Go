Feature: User able to register
  Scenario: User Registration
    Given I am on the registration page
    When I enter valid registration details (username, email, password)
    Then I should be successfully registered