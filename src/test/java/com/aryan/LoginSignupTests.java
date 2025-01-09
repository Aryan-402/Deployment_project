package com.aryan;

import org.openqa.selenium.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

public class LoginSignupTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:2306/Deployment_project/");
    }

    @Given("the user navigates to the login page")
    public void theUserNavigatesToLoginPage() {
        driver.findElement(By.linkText("Login")).click();
    }

    @When("the user enters a valid username and password")
    public void theUserEntersValidCredentials() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        usernameField.sendKeys("testuser");  // Valid username
        passwordField.sendKeys("testpassword");  // Valid password
    }

    @When("the user enters an invalid username and password")
    public void theUserEntersInvalidCredentials() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        usernameField.sendKeys("wronguser");
        passwordField.sendKeys("wrongpassword");
    }

    @When("the user clicks the login button")
    public void theUserClicksLoginButton() {
        WebElement loginButton = driver.findElement(By.name("submit"));
        loginButton.click();
    }

    @Then("the user should be redirected to the dashboard or home page")
    public void theUserShouldBeRedirectedToHomePage() {
        // Example: Check for a "Welcome" message (or home page element)
        WebElement welcomeMessage = driver.findElement(By.xpath("//h1[contains(text(),'Welcome')]"));
        Assert.assertNotNull(welcomeMessage);
    }

    @Then("a welcome message should be displayed")
    public void aWelcomeMessageShouldBeDisplayed() {
        WebElement welcomeMessage = driver.findElement(By.xpath("//h1[contains(text(),'Welcome')]"));
        Assert.assertNotNull(welcomeMessage);
    }

    @Then("an error message should be displayed indicating invalid credentials")
    public void anErrorMessageShouldBeDisplayed() {
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'error')]"));
        Assert.assertNotNull(errorMessage);
    }

    @Given("the user navigates to the signup page")
    public void theUserNavigatesToSignupPage() {
        driver.findElement(By.linkText("Signup")).click();
    }

    @When("the user enters a valid username, password, and email")
    public void theUserEntersValidSignupDetails() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement confirmPasswordField = driver.findElement(By.name("confirm_password"));
        WebElement emailField = driver.findElement(By.name("email"));

        usernameField.sendKeys("newuser");
        passwordField.sendKeys("newpassword");
        confirmPasswordField.sendKeys("newpassword");
        emailField.sendKeys("newuser@example.com");
    }

    @When("the user confirms the password and clicks the signup button")
    public void theUserClicksSignupButton() {
        WebElement signupButton = driver.findElement(By.name("submit"));
        signupButton.click();
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToLoginPage() {
        // Check for successful redirection to the login page
        WebElement loginPageHeader = driver.findElement(By.xpath("//h1[contains(text(),'Login')]"));
        Assert.assertNotNull(loginPageHeader);
    }

    @Then("a registration success message should be displayed")
    public void aRegistrationSuccessMessageShouldBeDisplayed() {
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Registration successful')]"));
        Assert.assertNotNull(successMessage);
    }

    @When("the user enters a valid username, mismatched password, and email")
    public void theUserEntersInvalidSignupDetails() {
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement confirmPasswordField = driver.findElement(By.name("confirm_password"));
        WebElement emailField = driver.findElement(By.name("email"));

        usernameField.sendKeys("newuser");
        passwordField.sendKeys("newpassword");
        confirmPasswordField.sendKeys("differentpassword");  // Mismatched passwords
        emailField.sendKeys("newuser@example.com");
    }

    @Then("an error message should be displayed indicating password mismatch")
    public void anErrorMessageShouldBeDisplayedForPasswordMismatch() {
        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'error')]"));
        Assert.assertNotNull(errorMessage);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
