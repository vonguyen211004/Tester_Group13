package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC09 {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    private static final String URL = "http://railwayb1.somee.com/";
    private static final String USERNAME = System.getenv("RAILWAY_USERNAME") != null
            ? System.getenv("RAILWAY_USERNAME")
            : "vovanbaonguyen19@gmail.com";
    private static final String PASSWORD = System.getenv("RAILWAY_PASSWORD") != null
            ? System.getenv("RAILWAY_PASSWORD")
            : "baonguyen2004";
    private final String newPassword = "baonguyen123";

    @Test
    public void TC09_NguoiDungCoTheThayDoiMatKhau() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            js = (JavascriptExecutor) driver;

            openUrl(URL);
            login(USERNAME, PASSWORD);
            changePassword(PASSWORD, newPassword);
            verifyMessage("//p[@class='message success']", "Your password has been updated!");
            changePassword(newPassword, PASSWORD);
            System.out.println("Password reverted successfully.");
        } catch (Exception e) {
            logError("Error executing TC09", e);
            Assert.fail("TC09 failed: " + e.getMessage());
        } finally {
            if (driver != null) {
                System.out.println("Closing browser...");
                driver.quit();
            }
        }
    }

    private void openUrl(String url) {
        driver.get(url);
    }

    private void login(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    private void changePassword(String currentPassword, String newPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Change password"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentPassword"))).sendKeys(currentPassword);
        driver.findElement(By.id("newPassword")).sendKeys(newPassword);
        driver.findElement(By.id("confirmPassword")).sendKeys(newPassword);

        WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        js.executeScript("arguments[0].click();", submitBtn);
    }

    private void verifyMessage(String xpath, String expectedMessage) {
        String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Message does not match!");
    }

    private void logError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
    }
}
