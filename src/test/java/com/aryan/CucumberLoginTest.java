package com.aryan;

import com.aryan.DatabaseConnection;
import com.aryan.LoginServlet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class CucumberLoginTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> mockedStatic;
    private LoginServlet loginServlet;

    @Given("a valid user login request")
    public void aValidUserLoginRequest() throws Exception {
        // Mock dependencies
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Mock static DatabaseConnection.getConnection
        mockedStatic = Mockito.mockStatic(DatabaseConnection.class);
        mockedStatic.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

        // Mock request parameters
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getContextPath()).thenReturn(""); // Simulate context path as empty string

        // Mock SQL behavior
        when(mockConnection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?"))
                .thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simulate a valid login

        // Mock session behavior
        when(request.getSession()).thenReturn(session);

        // Create servlet instance
        loginServlet = new LoginServlet();
    }

    @When("the login servlet processes the request")
    public void theLoginServletProcessesTheRequest() throws Exception {
        // Call doPost method
        loginServlet.doPost(request, response);
    }

    @Then("the user should be successfully logged in and redirected")
    public void theUserShouldBeSuccessfullyLoggedInAndRedirected() throws Exception {
        // Verify session and redirection
        verify(session).setAttribute("username", "testuser");
        verify(response).sendRedirect("/welcome.jsp");

        // Close the mocked static
        mockedStatic.close();
    }
}

