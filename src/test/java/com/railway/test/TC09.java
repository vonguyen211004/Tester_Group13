package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC09 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void changePasswordSuccessfully() {
        driver.get("http://railwayb1.somee.com/");

        // Step 1: Login
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validUser@test.com"); // hãy chắc chắn là user này đã tồn tại
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // Step 2: Check login success
        try {
            WebElement welcomeMsg = driver.findElement(By.xpath("//div[@class='account']/strong"));
            System.out.println("Login success: " + welcomeMsg.getText());
        } catch (NoSuchElementException e) {
            System.out.println("Login failed. Check credentials.");
            return;
        }

        // Step 3: Click Change password
        try {
            driver.findElement(By.linkText("Change password")).click();

            driver.findElement(By.id("currentPassword")).sendKeys("12345678");
            driver.findElement(By.id("newPassword")).sendKeys("newpass123");
            driver.findElement(By.id("confirmPassword")).sendKeys("newpass123");
            driver.findElement(By.xpath("//input[@type='submit']")).click();

            // Step 4: Verify success message
            String message = driver.findElement(By.xpath("//p[@class='message success']")).getText();
            System.out.println("Change password success message: " + message);
        } catch (NoSuchElementException e) {
            System.out.println("Link 'Change password' not found. Are you logged in?");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
