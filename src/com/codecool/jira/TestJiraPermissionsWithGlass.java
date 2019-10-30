package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraPermissionsWithGlass extends MainTest{

    @BeforeAll
    public void setUp() {
        super.setUp();
        super.login();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("browse_link"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("project_view_all_link"))).click();
        webDriver.findElement(By.id("project-filter-text")).sendKeys("Private Project 4");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title='Private Project 4']"))).click(); // click on PP4 title
    }

    @BeforeEach
    void getProjectSettingsPage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content a[data-tooltip='Project settings']"))).click(); // click on Project setting icon
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("view_project_permissions"))).click();
    }

    // Permission matrix: all
    @Test
    public void testAllPermissions() {
        // TODO
    }

    // Permission matrix: Browse Project
    @Test
    public void testBrowseProjectsPermission() {
        String browsePermission1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#project-config-panel-permissions .permissions-group > .jira-admin-table > tbody > tr[data-permission-key='BROWSE_PROJECTS'] > .grants > .types > .sentence-case"))).getText();
        String browsePermission2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#project-config-panel-permissions .permissions-group > .jira-admin-table > tbody > tr[data-permission-key='BROWSE_PROJECTS'] > .grants > .types > dd"))).getText();
        String browsePermission = browsePermission1 + ": " + browsePermission2;
        webDriver.findElement(By.cssSelector(".aui-nav-item[data-link-id='com.codecanvas.glass:glass']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("glass-permissions-nav"))).click();
        String glassBrowsePermission1;
        String glassBrowsePermission2;
        String glassBrowsePermission;
        // Assertions.assertEquals(browsePermission, glassBrowsePermission);
        // TODO glassProjectPart
    }

    // Permission matrix: Create Issues
    @Test
    public void testCreateIssuesPermission() {
        String createIssuesPermission1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#project-config-panel-permissions .permissions-group > .jira-admin-table > tbody > tr[data-permission-key='CREATE_ISSUES'] > .grants > .types > .sentence-case"))).getText();
        String createIssuesPermission2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#project-config-panel-permissions .permissions-group > .jira-admin-table > tbody > tr[data-permission-key='CREATE_ISSUES'] > .grants > .types > dd"))).getText();
        String createIssuesPermission = createIssuesPermission1 + ": " + createIssuesPermission2;
        webDriver.findElement(By.cssSelector(".aui-nav-item[data-link-id='com.codecanvas.glass:glass']")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // TODO glassCreateIssues
    }

    // Permission matrix: Edit Issue
    @Test
    public void testEditIssuesPermission() {
        String editIssuesPermission1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#project-config-panel-permissions .permissions-group > .jira-admin-table > tbody > tr[data-permission-key='EDIT_ISSUES'] > .grants > .types > .sentence-case"))).getText();
        String editIssuesPermission2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#project-config-panel-permissions .permissions-group > .jira-admin-table > tbody > tr[data-permission-key='EDIT_ISSUES'] > .grants > .types > dd"))).getText();
        String editIssuesPermission = editIssuesPermission1 + ": " + editIssuesPermission2;
        webDriver.findElement(By.cssSelector(".aui-nav-item[data-link-id='com.codecanvas.glass:glass']")).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // TODO glassEditIssues
    }

    @AfterAll
    public void tearDown() {
        super.logout();
        super.tearDown();
    }

}
