Feature: Admin is able to Login and
  Scenario: User Registration
    Given I am on the registration page
    When I enter valid registration details (username, email, password)
    Then I should be successfully registered

Scenario: User Login
  Given I am on the login page
  When I enter valid login credentials (username, password)
  Then I should be logged in successfully

  Scenario: Get All Users
    Given there are multiple users in the system
    When the client requests to get all users
    Then the response should contain a list of all users

  Scenario: Get User by ID
    Given user is an Admin
    When I search for a user by Id
    Then the response should contain the user details

  Scenario: Update User
    Given user is an Admin
    When I update a users details
    Then the user should be successfully updated