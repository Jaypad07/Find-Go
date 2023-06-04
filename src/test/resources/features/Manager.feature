Feature: Manager is able to login and manipulate Store data
  Scenario: Manager logins in and requests to get all stores
    Given Manager is logged in

  Scenario: Manager wants to get a store by ID
    Given the user is a Manager
    When the Manager sends a request to get the store by ID
    Then the response should contain the store details

  Scenario: User adds a new store section to a store
    Given  the user is a Manager
    When the user submits the store section details
    Then the store section should be added successfully

  Scenario: User updates an existing store section of a store
    Given the user is a Manager
    When the user submits the updated store section details
    Then the store section should be updated successfully

  Scenario: Manager adds a new product
    Given the user is a Manager
    When the manager submits the product details
    Then the product should be added successfully

  Scenario: Manager requests to get a product by ID
    Given  the user is a Manager
    When the Manager sends a request to get the product by ID
    Then the response should contain the product details







