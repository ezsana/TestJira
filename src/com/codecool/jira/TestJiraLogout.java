package com.codecool.jira;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestJiraLogout extends MainTest{

    @BeforeAll
    public void setUp() {
       super.setUp();
    }

    // Logout option works
    @Test
    public void logout() {
        login();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("log_out"))).click();
        Assertions.assertEquals("Logout", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section[id='content'] h1"))).getText());
        Assertions.assertEquals("You are now logged out. Any automatic login has also been stopped.", wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section[id='content'] .title"))).getText());
    }

    @AfterAll
    public void tearDown() {
        super.tearDown();
    }

}
