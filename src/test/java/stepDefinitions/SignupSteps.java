package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignupSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the signup page")
    public void iAmOnTheSignupPage() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Runs browser in headless mode (for Jenkins)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:2306/Deployment_project/signup.jsp");
    }

    @When("I enter {string} as username")
    public void iEnterAsUsername(String username) {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
    }

    @When("I enter {string} as email")
    public void iEnterAsEmail(String email) {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    }

    @When("I enter {string} as password")
    public void iEnterAsPassword(String password) {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }

    @And("I click the Signup button")
    public void iClickTheSignupButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        wait.until(ExpectedConditions.urlContains("/login.jsp"));
        Assert.assertTrue("User is not redirected to login page!", driver.getCurrentUrl().contains("/login.jsp"));
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error-message']")));
        Assert.assertTrue("Error message does not match!", errorMsg.getText().trim().contains(expectedMessage));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
