Feature: Login functionality for users

  Scenario: Valid user login
    Given a valid user login request
    When the login servlet processes the request
    Then the user should be successfully logged in and redirected