package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraBrowseIssue extends MainTest {

    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    @BeforeEach
    public void login() {
        super.login();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("find_link"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("issues_new_search_link_lnk"))).click();
    }

    // Browse issues available
    @Test
    void browseIssues() {
        browseAnyIssue("MTP-664");
    }

    // Search for issues / TOUCAN
    @Test
    void searchToucanIssues1() {
        browseAnyIssue("TOUCAN-1");
    }


    // This will fail with user8
    @Test
    void searchToucanIssues2() {
        browseAnyIssue("TOUCAN-2");
    }

    @Test
    void searchToucanIssues3() {
        browseAnyIssue("TOUCAN-3");
    }


    // Search for issues / JETI

    // This will fail with user8
    @Test
    void searchJetiIssues1() {
        browseAnyIssue("JETI-1");
    }

    @Test
    void searchJetiIssues2() {
        browseAnyIssue("JETI-2");
    }

    @Test
    void searchJetiIssues3() {
        browseAnyIssue("JETI-3");
    }

    // Search for issues / COALA

    // This will fail with user8
    @Test
    void searchCoalaIssues1() {
        browseAnyIssue("COALA-1");
    }

    // This will fail with user8
    @Test
    void searchCoalaIssues2() {
        browseAnyIssue("COALA-2");
    }

    // This will fail with user8
    @Test
    void searchCoalaIssues3() {
        browseAnyIssue("COALA-3");
    }

    public void browseAnyIssue(String issueName) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advanced-search"))).sendKeys("issuekey =  " + issueName);
        webDriver.findElement(By.cssSelector(".search-options-container > .aui-button[original-title='Search for issues']")).click();
        Assertions.assertEquals(issueName, wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".issue-link[data-issue-key='" + issueName + "']"))).getText());
    }

    @AfterEach
    public void logout() {
        super.logout();
    }

    @AfterAll
    public void tearDown() {
        super.tearDown();
    }

}
