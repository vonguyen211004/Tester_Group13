package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01 extends baseTest {

    @Test
    public void validLogin() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("vovanbaonguyen19@gmail.com");
        driver.findElement(By.id("password")).sendKeys("baonguyen");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement welcomeMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account > strong")));

        String actualWelcomeMsg = welcomeMsg.getText();

        Assert.assertTrue(actualWelcomeMsg.contains("Welcome"),
                "The Welcome! message is not displayed." +
                        "Actual result: " + actualWelcomeMsg);
    }
}
