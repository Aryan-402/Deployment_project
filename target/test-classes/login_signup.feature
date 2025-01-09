Feature: Login and Signup functionality of the application

  # Scenario for valid login
  Scenario: User should be able to login with valid credentials
    Given the user navigates to the login page
    When the user enters a valid username and password
    And the user clicks the login button
    Then the user should be redirected to the dashboard or home page
    And a welcome message should be displayed

  # Scenario for invalid login
  Scenario: User should not be able to login with invalid credentials
    Given the user navigates to the login page
    When the user enters an invalid username and password
    And the user clicks the login button
    Then an error message should be displayed indicating invalid credentials

  # Scenario for valid signup
  Scenario: User should be able to sign up with valid information
    Given the user navigates to the signup page
    When the user enters a valid username, password, and email
    And the user confirms the password and clicks the signup button
    Then the user should be redirected to the login page
    And a registration success message should be displayed

  # Scenario for invalid signup (password mismatch)
  Scenario: User should not be able to sign up with mismatched passwords
    Given the user navigates to the signup page
    When the user enters a valid username, mismatched password, and email
    And the user confirms the password and clicks the signup button
    Then an error message should be displayed indicating password mismatch
