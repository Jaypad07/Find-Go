Feature: User is able to view stores and products
  Scenario: User requests to view a list of Stores
    Given the user wants to find all stores
    When the user sends a request to get all stores
    Then the response should contain a list of all stores
