Feature: Admin is able to register, login, and manipulate User & Store data
  Scenario: User Registration
    Given user is on the registration page
    When user enters valid registration details (username, email, password)
    Then user should be successfully registered

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

  Scenario: Delete User
    Given user is an Admin
    When I delete a user
    Then the user should be successfully deleted

  Scenario: Add store to database
    Given user is an Admin
    When user creates a store
    Then the store should successfully be added

  Scenario: Admin updates an existing store
    Given user is an Admin
    When the user submits the updated store details
    Then the store should be updated successfully

  Scenario: Admin deletes an existing store
    Given user is an Admin
    When the owner sends a request to delete the store
    Then the store should be deleted successfully