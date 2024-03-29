package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestJiraLogin {

    private static String driverPath = System.getenv("DRIVERPATH");
    private WebDriver webDriver;
    private String url = "https://jira.codecool.codecanvas.hu";
    private WebDriverWait wait;
    private String username = System.getenv("USERNAME");
    private String password = System.getenv("PASSWORD");
    private String incorrectPassword = System.getenv("INCORRECT_PASSWORD");
    private String incorrectUsername = System.getenv("INCORRECT_USERNAME");


    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 20);
    }

    // Login - Just login

    // Login with correct data - happy path
    @Test
    @Order(1)
    public void LoginWithCorrectData() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(password);
        webDriver.findElement(By.id("login")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("header-details-user-fullname")).click();
        webDriver.findElement(By.id("view_profile")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("user8", webDriver.findElement(By.id("up-d-username")).getText());
        Assertions.assertEquals("User 8", webDriver.findElement(By.id("up-d-fullname")).getText());
        Assertions.assertEquals("user8@user.com", webDriver.findElement(By.id("up-d-email")).getText());
        webDriver.findElement(By.id("header-details-user-fullname")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("log_out")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Unsuccessful login with correct username and incorrect password
    @Test
    @Order(2)
    public void UnsuccessfulLoginWithIncorrectData() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(incorrectPassword); // incorrect password
        webDriver.findElement(By.id("login")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String expectedErrorMessage = "Sorry, your username and password are incorrect - please try again.";
        String errorMessage = webDriver.findElement(By.cssSelector("div[id='usernameerror'] p")).getText();
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }

    // Empty login
    @Test
    @Order(3)
    public void EmptyLogin() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String expectedErrorMessage = "Sorry, your username and password are incorrect - please try again.";
        String errorMessage = webDriver.findElement(By.cssSelector("div[id='usernameerror'] p")).getText();
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }


    // Multiple unsuccessful login attempts - 3 times login attempt
    @Test
    @Order(7)
    public void multipleLoginAttempts() {
        webDriver.navigate().to(url);
        for (int i = 0; i < 3; i++) {
            webDriver.navigate().refresh();
            webDriver.findElement(By.id("login-form-username")).sendKeys(username);
            webDriver.findElement(By.id("login-form-password")).sendKeys(incorrectPassword); // incorrect password
            webDriver.findElement(By.id("login")).click();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        Assertions.assertTrue(webDriver.findElement(By.id("captchaimg")).isDisplayed());
    }

    // Login - Remember login data

    // "Remember my login on this computer" - with correct login data - after this test: unexpected alert open???? I had to
    // make it to log in and log out to let the rest of the tests succeed
    @Test
    @Order(6)
    public void rememberLoginData() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username); // Sys getenv
        webDriver.findElement(By.id("login-form-password")).sendKeys(password); // Sys getenv
        webDriver.findElement(By.id("login-form-remember-me")).click();
        WebElement rememberMeElement = webDriver.findElement(By.id("login-form-remember-me"));
        Assertions.assertTrue(rememberMeElement.isSelected());
        webDriver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log_out"))).click();
    }

    // "Remember my login on this computer" - with incorrect login data
    @Test
    @Order(4)
    public void rememberIncorrectLoginData() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(incorrectUsername); // Sys getenv
        webDriver.findElement(By.id("login-form-password")).sendKeys(incorrectPassword);  // Sys getenv
        webDriver.findElement(By.id("login-form-remember-me")).click();
        WebElement rememberMeElement = webDriver.findElement(By.id("login-form-remember-me"));
        Assertions.assertTrue(rememberMeElement.isSelected());
        webDriver.findElement(By.id("login")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String expectedErrorMessage = "Sorry, your username and password are incorrect - please try again.";
        String errorMessage = webDriver.findElement(By.cssSelector("div[id='usernameerror'] p")).getText();
        Assertions.assertEquals(expectedErrorMessage, errorMessage);
    }

    // Automatic login cancelled
    @Test
    @Order(5)
    public void automaticLoginCancelled() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username); // Sys getenv
        webDriver.findElement(By.id("login-form-password")).sendKeys(password); // Sys getenv
        webDriver.findElement(By.id("login")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("header-details-user-fullname")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.id("log_out")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Tests.jspa#/testCase/MTP-T866");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("header[id='header'] h1")));
        Assertions.assertTrue(webDriver.findElement(By.id("login-form-submit")).isDisplayed());
    }

    // Login - Captcha - These should be manual as all of the logins requires captcha message written by human

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
