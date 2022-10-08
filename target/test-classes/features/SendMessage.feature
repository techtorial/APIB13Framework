Feature: Slack post messages

  Scenario: Successfully sending message to slack channel
    Given user has valid slack url
    When user sends a message to slack channel
    Then status code is 200
    And message is successfully delivered