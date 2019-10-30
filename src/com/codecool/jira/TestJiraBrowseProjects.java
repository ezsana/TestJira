package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraBrowseProjects extends MainTest{

    @BeforeAll
    public void setUp() {
        super.setUp();
        login();
    }

    @BeforeEach
    public void goToBrowseAllProjectsLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("browse_link"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("project_view_all_link"))).click();
    }

    // Browsing projects available - test on an existing project: Main Testing Project
    @Test
    public void browsingProjects() {
        browseSpecificProjects("Main Testing Project", "Main Testing Project", "Main Testing Project");
    }

    // Browsing TOUCAN project
    @Test
    public void browsingToucanProject() {
        browseSpecificProjects("TOUCAN projekt", "TOUCAN projekt", "TOUCAN projekt");
    }

    // Browsing JETI project
    @Test
    public void browsingJetiProject() {
        browseSpecificProjects("JETI Project", "JETI Project", "JETI Project");
    }

    // Browsing COALA project
    @Test
    public void browsingCoalaProject() {
        browseSpecificProjects("COALA Project", "COALA Project", "COALA Project");
    }

    public void browseSpecificProjects(String projectname, String title, String projectHeading) {
        webDriver.findElement(By.id("project-filter-text")).sendKeys(projectname);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[title=" + "'"+ title + "']"))).click();
        Assertions.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(projectHeading))).isDisplayed());
    }

    @AfterAll
    public void tearDown() {
        logout();
        super.tearDown();
    }

}
