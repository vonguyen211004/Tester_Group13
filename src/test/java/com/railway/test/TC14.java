package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC14 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void bookOneTicket() {
        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validEmail");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        driver.findElement(By.linkText("Book ticket")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String message = driver.findElement(By.xpath("//div[@id='content']")).getText();
        System.out.println("Booking Result: " + message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
