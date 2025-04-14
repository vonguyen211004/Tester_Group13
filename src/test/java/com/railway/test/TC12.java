package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TC12 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup WebDriver using WebDriverManager and maximize the window for better visibility
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void blankTokenResetPassword() {
        // Navigate to the reset password page
        driver.get("http://railwayb1.somee.com/Account/ForgotPassword.cshtml");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for token input field and leave it blank
            WebElement tokenField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("token")));
            tokenField.sendKeys(""); // Blank token

            // Enter new password
            WebElement newPasswordField = driver.findElement(By.id("newPassword"));
            newPasswordField.sendKeys("12345678");

            // Enter confirm password
            WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
            confirmPasswordField.sendKeys("12345678");

            // Scroll and click the submit button using JavaScript
            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

            // Wait for the error message to appear and verify its content
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='message error']")));
            String error = errorMessage.getText();

            // Assert the error message content
            assertEquals(error, "Token is required.", "Error message verification failed!");

            System.out.println("Reset error: " + error);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}