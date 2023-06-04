Feature: User is able to view stores and products
  Scenario: User requests to view a list of Stores
    Given the user wants to find all stores
    When the user sends a request to get all stores
    Then the response should contain a list of all stores

  Scenario: User requests to get a store by name
    Given a store is available
    When the user sends a request to get the store by name
    Then the response should return the store details

  Scenario: User requests to get a store by city
    Given the user wants to get a store by city
    When the user sends a request to get the store by city
    Then the response should contain the store information

  Scenario: User requests to get all products
    Given the user wants to get all products
    When the user sends a request to get all products
    Then the response should contain a list of all products

  Scenario: User requests to get a product by name
    Given the user wants to get a product by name
    When the user sends a request to get the product by name
    Then the response should contain the product details

  Scenario: User requests to get products by category
    Given the user wants to get products by category
    When the user sends a request to get the products by category
    Then the response should contain a list of products in the specified category

  Scenario: User updates an existing product
    Given the user wants to update an existing product
    When the user submits the updated product details
    Then the product should be updated successfully

  Scenario: User deletes an existing product
    Given the user wants to delete an existing product
    When the user sends a request to delete the product
    Then the product should be deleted successfully