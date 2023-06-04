Feature: Manager is able to login and manipulate Store data

  Scenario: Manager logins in and requests to get all stores
    Given Manager is logged in and a list of store is available
    When a manager searches for a list of stores
    Then the manager sees a list of stores


