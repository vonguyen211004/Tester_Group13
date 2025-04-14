package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC08 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginUnactivatedAccount() {
        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("username")).sendKeys("unactivated@test.com");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String error = driver.findElement(By.xpath("//p[@class='message error LoginForm']")).getText();
        System.out.println("Error: " + error);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
