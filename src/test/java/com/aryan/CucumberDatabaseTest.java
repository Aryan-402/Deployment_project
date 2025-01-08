package com.aryan;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.SQLException;

public class CucumberDatabaseTest {

    private Connection connection;

    @Given("the database connection is ready to be tested")
    public void theDatabaseConnectionIsReadyToBeTested() {
        // This step ensures the precondition for the test
    }

    @When("the application attempts to establish a connection")
    public void theApplicationAttemptsToEstablishAConnection() {
        try {
            // Attempt to establish a connection
            connection = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            Assert.fail("Exception occurred while establishing database connection: " + e.getMessage());
        }
    }

    @Then("the database connection should be successfully established")
    public void theDatabaseConnectionShouldBeSuccessfullyEstablished() {
        // Verify the connection is not null
        Assert.assertNotNull(connection, "Database connection should not be null.");
    }

    @Then("the database connection should be valid")
    public void theDatabaseConnectionShouldBeValid() {
        try {
            // Verify the connection is valid
            Assert.assertTrue(connection.isValid(2), "Database connection should be valid.");
        } catch (SQLException e) {
            Assert.fail("Exception occurred while validating database connection: " + e.getMessage());
        } finally {
            // Ensure the connection is closed to release resources
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }
}
