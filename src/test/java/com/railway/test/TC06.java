package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC06 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void checkPagesAfterLogin() {
        driver.get("http://railwayb1.somee.com");
        // Login with valid account
        // Verify presence of additional pages like "My Ticket", "Change Password", etc.
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}