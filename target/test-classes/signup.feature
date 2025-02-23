Feature: User Signup

  Scenario: Successful signup with valid details
    Given I am on the signup page
    When I enter "testuser" as username
    And I enter "testuser@gmail.com" as email
    And I enter "password123" as password
    And I click the Signup button
    Then I should be redirected to the login page

  Scenario Outline: Signup fails with short password
    Given I am on the signup page
    When I enter "<username>" as username
    And I enter "<email>" as email
    And I enter "<password>" as password
    And I click the Signup button
    Then I should see an error message "<expectedMessage>"

    Examples:
      | username   | email                | password | expectedMessage                                |
      | testuser   | testuser@gmail.com   | 123      | Password must be at least 6 characters long.  |
