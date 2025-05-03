package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC03 extends baseTest {

    @Test
    public void invalidPasswordLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
            loginLink.click();

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("vovanbaonguyen19");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("#@%");

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();

            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.error")));

            String actualErrorMsg = errorMsg.getText();
            String expectedErrorMsg = "Invalid username or password. Please try again.";
            Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
                    "Error message does not match! Actual: " + actualErrorMsg);

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test encountered an exception: " + e.getMessage());
        }
    }
}