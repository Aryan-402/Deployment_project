package com.aryan;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServletTest extends Mockito {

    private SignupServlet signupServlet;

    @BeforeMethod
    public void setUp() {
        signupServlet = new SignupServlet();
    }

    @Test
    public void testValidSignup() throws Exception {
        // Mock dependencies
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Stub methods
        when(request.getParameter("username")).thenReturn("newUser");
        when(request.getParameter("email")).thenReturn("newuser@example.com");
        when(request.getParameter("password")).thenReturn("securePassword");

        // Execute servlet method
        signupServlet.doPost(request, response);

        // Verify behaviors
        verify(response).sendRedirect("/login.jsp");
    }

    @Test
    public void testDuplicateUserSignup() throws Exception {
        // Mock dependencies
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        // Stub methods
        when(request.getParameter("username")).thenReturn("existingUser");
        when(request.getParameter("email")).thenReturn("existing@example.com");
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getRequestDispatcher("signup.jsp")).thenReturn(dispatcher);

        // Execute servlet method
        signupServlet.doPost(request, response);

        // Verify behaviors
        verify(request).setAttribute("errorMessage", "Username or email already exists.");
        verify(dispatcher).forward(request, response);
    }
}
