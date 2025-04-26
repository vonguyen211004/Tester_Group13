package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC06 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkPagesAfterLogin() {
        driver.get("http://railwayb1.somee.com");

        // Click Login tab
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();

        // Login with valid credentials
        driver.findElement(By.id("username")).sendKeys("vovanbaonguyen19@gmail.com");
        driver.findElement(By.id("password")).sendKeys("baonguyen2004");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // Wait until login is successful and tabs appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My ticket")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Change password")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

        // Verify tabs are displayed
        assertTrue(driver.findElement(By.linkText("My ticket")).isDisplayed(), "'My ticket' tab not found.");
        assertTrue(driver.findElement(By.linkText("Change password")).isDisplayed(), "'Change password' tab not found.");
        assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed(), "'Logout' tab not found.");

        // Click My ticket tab
        driver.findElement(By.linkText("My ticket")).click();
        wait.until(ExpectedConditions.urlContains("ManageTicket"));

        // Click Change password tab
        driver.findElement(By.linkText("Change password")).click();
        wait.until(ExpectedConditions.urlContains("ChangePassword"));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
