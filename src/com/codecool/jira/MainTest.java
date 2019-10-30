package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {

    static String driverPath = System.getenv("DRIVERPATH");
    WebDriver webDriver;
    String url = "https://jira.codecool.codecanvas.hu";
    WebDriverWait wait;
    String username = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");
    String incorrectPassword = System.getenv("INCORRECT_PASSWORD");
    String incorrectUsername = System.getenv("INCORRECT_USERNAME");

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 20);
        webDriver.manage().window().maximize();
    }

    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public void login() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(password);
        webDriver.findElement(By.id("login")).click();
    }

    public void unsuccessfulLogin() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(incorrectPassword); // incorrect password
        webDriver.findElement(By.id("login")).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log_out"))).click();
    }

}
