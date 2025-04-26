package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC10 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void confirmPasswordMismatch() {
        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("email")).sendKeys("mismatch@test.com");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("confirmPassword")).sendKeys("87654321");
        driver.findElement(By.id("pid")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String error = driver.findElement(By.xpath("//label[@for='confirmPassword' and @class='validation-error']")).getText();
        System.out.println("Validation error: " + error);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
