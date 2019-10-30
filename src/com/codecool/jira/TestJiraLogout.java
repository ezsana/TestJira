package com.codecool.jira;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraLogout {

    private static String driverPath = System.getenv("DRIVERPATH");
    private WebDriver webDriver;
    private String url = "https://jira.codecool.codecanvas.hu";
    private WebDriverWait wait;
    private String username = System.getenv("USERNAME");
    private String password = System.getenv("PASSWORD");

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 20);
    }

    // Logout option works
    @Test
    public void logout() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(password);
        webDriver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log_out"))).click();
        Assertions.assertEquals("Logout", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section[id='content'] h1"))).getText());
        Assertions.assertEquals("You are now logged out. Any automatic login has also been stopped.", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section[id='content'] .title"))).getText());
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
