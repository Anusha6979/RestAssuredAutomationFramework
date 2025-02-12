@api @messages
Feature: Message API Testing

  Scenario: Retrieve all messages when none exist
    When I send a GET request to "/message"
    Then the response status code should be 200
    And the response body should not be empty

  Scenario: Create a new message with invalid data (missing required fields)
    When I send a POST request with the following invalid data
      | name  | email   | phone      | subject | description |
      | John  |         | 1234567890 | Test    | Test message |
    Then the response status code should be 400
    And the error message should be "Email may not be blank"

  Scenario: Update a non-existent message
    Given I have created a message
    When I update the message with a non-existent ID
    Then the response status code should be 403

  Scenario: Delete a non-existent message
    Given I have created a message
    When I send a DELETE request for a non-existent message
    Then the response status code should be 403

  Scenario: Retrieve a message and validate its content
    Given I have created a message
    When I send a GET request to "/message" retrieve
    Then the response status code should be 200
    And the message name should be "John Doe"
    And the message email should be "john@example.com"
    And the message phone should be "12345678901"
    And the message subject should be "Test message"
    And the message description should be "Message should be proper"
