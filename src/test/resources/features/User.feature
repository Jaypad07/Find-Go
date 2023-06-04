Feature: User able to register & login
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

#  Scenario: Get User by ID
#    Given a user with ID {userId}
#    When the client requests to get the user by ID {userId}
#    Then the response should contain the user details

