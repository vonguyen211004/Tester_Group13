package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC10 {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @Test
    public void confirmPasswordMismatch() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;

        try {
            driver.get("http://railwayb1.somee.com/");
            driver.findElement(By.linkText("Register")).click();

            driver.findElement(By.id("email")).sendKeys("mismatch@test.com");
            driver.findElement(By.id("password")).sendKeys("12345678");
            driver.findElement(By.id("confirmPassword")).sendKeys("87654321");
            driver.findElement(By.id("pid")).sendKeys("123456789");

            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
            js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
            wait.until(ExpectedConditions.elementToBeClickable(submitButton));

            Actions actions = new Actions(driver);
            actions.moveToElement(submitButton).click().perform();

            String error = driver.findElement(By.xpath("//label[@for='confirmPassword' and @class='validation-error']")).getText();
            System.out.println("Validation error: " + error);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
