package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraBrowseProjects {

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
        webDriver.manage().window().maximize();
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(password);
        webDriver.findElement(By.id("login")).click();
    }

    @BeforeEach
    public void gotToBrowseLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("browse_link"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("project_view_all_link"))).click();
    }

    // Browsing projects available
    @Test
    public void browsingProjects() {
        webDriver.findElement(By.id("project-filter-text")).sendKeys("Main Testing Project");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='Main Testing Project']"))).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Main Testing Project"))).isDisplayed());
    }

    // Browsing TOUCAN project
    @Test
    public void browsingToucanProject() {
        webDriver.findElement(By.id("project-filter-text")).sendKeys("TOUCAN projekt");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='TOUCAN projekt']"))).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("TOUCAN projekt"))).isDisplayed());
    }

    // Browsing JETI project
    @Test
    public void browsingJetiProject() {
        webDriver.findElement(By.id("project-filter-text")).sendKeys("JETI Project");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='JETI Project']"))).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("JETI Project"))).isDisplayed());
    }

    // Browsing COALA project
    @Test
    public void browsingCoalaProject() {
        webDriver.findElement(By.id("project-filter-text")).sendKeys("COALA Project");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='COALA Project']"))).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("COALA Project"))).isDisplayed());
    }


    @AfterAll
    public void tearDown() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log_out"))).click();
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
