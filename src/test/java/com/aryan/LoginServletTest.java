package com.aryan;

import org.mockito.*;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    @Test
    public void testValidLogin() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("password")).thenReturn("testPassword");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        // Mock the database connection and ResultSet (for login verification)
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true); // Simulate successful login

        LoginServlet loginServlet = new LoginServlet();
        loginServlet.doPost(request, response);

        verify(request).getRequestDispatcher("/welcome.jsp"); // Verify the redirection
    }
}
