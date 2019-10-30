package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestJiraVersionsWithGlass {

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
        wait = new WebDriverWait(webDriver, 30);
        webDriver.manage().window().maximize();
        webDriver.navigate().to(url);
        webDriver.findElement(By.id("login-form-username")).sendKeys(username);
        webDriver.findElement(By.id("login-form-password")).sendKeys(password);
        webDriver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("browse_link"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("project_view_all_link"))).click();
        webDriver.findElement(By.id("project-filter-text")).sendKeys("Private Project 4");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='Private Project 4']"))).click(); // click on PP4 title
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-link-id='com.codecanvas.glass:glass']"))).click(); // click on glass doc icon
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("aui-uid-2"))).click(); // click on Version tab
    }

    // Private Project / Glass Page / All versions up-to-date - to check this use Version 5.0
    @Test
    @Order(1)
    public void AreAllVersionsUpToDate() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String releaseText = webDriver.findElement(By.cssSelector("#versions-table > .items > tr:nth-child(5) > .versions-table__status > div > span")).getText();
        Assertions.assertEquals("UNRELEASED".toLowerCase(), releaseText.toLowerCase());
    }

    // Private project 4 / Version links
    @Test
    @Order(3)
    public void AreVersionLinksWorking() {
        String glassUrl = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("5.0"))).getAttribute("href"); // the attribute value of the link
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content a[data-tooltip='Project settings']"))).click(); // click on Project setting icon
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("administer_project_versions"))).click(); // click on versions in sidebar
        String projectVersionUrl = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("5.0"))).getAttribute("href"); // click on 5.0 project
        Assertions.assertEquals(projectVersionUrl, glassUrl);
    }

    // Version attributes in glass documentation
    @Test
    @Order(2)
    public void AreVersionAttributesMatchingInGlass() {
        // get all the version attributes from Glass
        String glassVersionNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(5) > .versions-table__name a"))).getText();
        String glassVersionStatus = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(5) > .versions-table__status span"))).getText();
        String glassVersionStartdate = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(5) > .versions-table__date_start > div"))).getText();
        String glassVersionDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(5) .versions-table__description > div"))).getText();
        webDriver.findElement(By.cssSelector("span[title='Releases']")).click(); // click on Releases in sidebar
        // get the version attributes from project's own Jira page:
        String realVersionNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(1) > .versions-table__name a"))).getText();
        String realVersionStatus = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(1) > .versions-table__status span"))).getText();
        String realVersionStartdate = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(1) > .versions-table__date_start time"))).getText();
        String realVersionDescription = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#versions-table > .items > tr:nth-child(1) > .versions-table__description > div"))).getText();
        // Compare the two:
        Assertions.assertEquals(realVersionNumber, glassVersionNumber);
        Assertions.assertEquals(realVersionStatus, glassVersionStatus);
        Assertions.assertEquals(realVersionStartdate, glassVersionStartdate);
        Assertions.assertEquals(realVersionDescription, glassVersionDescription);
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
