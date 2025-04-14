package com.railway.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC05 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void wrongPasswordSeveralTimes() {
        driver.get("http://railwayb1.somee.com");
        for (int i = 0; i < 5; i++) {
            // Login steps with invalid password
        }
        // Assert error message for too many wrong attempts
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
