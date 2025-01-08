package com.aryan;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.mockito.Mockito.*;

public class CucumberSignupTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private MockedStatic<DatabaseConnection> mockedStatic;
    private SignupServlet signupServlet;

    @Given("a valid user signup request")
    public void aValidUserSignupRequest() throws Exception {
        // Mock dependencies
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);

        // Mock request parameters
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("email")).thenReturn("testuser@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getContextPath()).thenReturn("");

        // Mock static DatabaseConnection.getConnection
        mockedStatic = Mockito.mockStatic(DatabaseConnection.class);
        mockedStatic.when(DatabaseConnection::getConnection).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // Simulate successful insertion

        // Create servlet instance
        signupServlet = new SignupServlet();
    }

    @When("the signup servlet processes the request")
    public void theSignupServletProcessesTheRequest() throws Exception {
        // Call doPost method
        signupServlet.doPost(request, response);
    }

    @Then("the user should be successfully signed up and redirected")
    public void theUserShouldBeSuccessfullySignedUpAndRedirected() throws Exception {
        // Verify redirection
        verify(response).sendRedirect("/login.jsp");

        // Close the mocked static
        mockedStatic.close();
    }
}
