package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraBrowseIssue {

    private static String driverPath = "/home/zsana/chromedriver.exe";
    private WebDriver webDriver;
    private String url = "https://jira.codecool.codecanvas.hu";
    private WebDriverWait wait;

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 20);
        webDriver.manage().window().maximize();
    }

    @BeforeEach
    public void login() {
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys("user8");
        webDriver.findElement(By.id("login-form-password")).sendKeys("CoolCanvas19.");
        webDriver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find_link"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("issues_new_search_link_lnk"))).click();
    }

    // Browse issues available
    @Test
    void browseIssues() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey = MTP-664");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("MTP-664", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='MTP-664']"))).getText());
    }

    // Search for issues / TOUCAN
    @Test
    void searchToucanIssues1() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  TOUCAN-1");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("TOUCAN-1", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='TOUCAN-1']"))).getText());
    }


    // This will fail with user8
    @Test
    void searchToucanIssues2() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  TOUCAN-2");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("TOUCAN-2", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='TOUCAN-2']"))).getText());
    }

    @Test
    void searchToucanIssues3() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  TOUCAN-3");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("TOUCAN-3", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='TOUCAN-3']"))).getText());
    }



    // Search for issues / JETI

    // This will fail with user8
    @Test
    void searchJetiIssues1() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  JETI-1");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("JETI-1", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='JETI-1']"))).getText());
    }

    @Test
    void searchJetiIssues2() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  JETI-2");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("JETI-2", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='JETI-2']"))).getText());
    }

    @Test
    void searchJetiIssues3() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  JETI-3");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("JETI-3", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='JETI-3']"))).getText());
    }

    // Search for issues / COALA

    // This will fail with user8
    @Test
    void searchCoalaIssues1() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  COALA-1");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("COALA-1", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='COALA-1']"))).getText());
    }

    // This will fail with user8
    @Test
    void searchCoalaIssues2() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  COALA-2");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("COALA-2", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='COALA-2']"))).getText());
    }

    // This will fail with user8
    @Test
    void searchCoalaIssues3() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  COALA-3");
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals("COALA-3", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='COALA-3']"))).getText());
    }

    @AfterEach
    public void logout() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log_out"))).click();
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
