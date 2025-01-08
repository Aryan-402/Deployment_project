Feature: Signup functionality for users

  Scenario: Valid user signup
    Given a valid user signup request
    When the signup servlet processes the request
    Then the user should be successfully signed up and redirected
