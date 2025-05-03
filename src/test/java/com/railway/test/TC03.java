<<<<<<< HEAD
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
            // Navigate to the Login page
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
            loginLink.click();

            // Enter invalid login credentials
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("validUsername");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("wrongPassword");

            WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();

            // Wait for the error message to appear
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.error")));

            // Verify the error message
            String actualErrorMsg = errorMsg.getText();
            String expectedErrorMsg = "Invalid username or password. Please try again.";
            Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
                    "Error message does not match! Actual: " + actualErrorMsg);

        } catch (Exception e) {
            // Log the exception for debugging
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test encountered an exception: " + e.getMessage());
        }
    }
}
=======
package com.railway.test;//package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03 extends baseTest {

    @Test
    public void invalidPasswordLogin() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validUsername");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".message.error"));
        Assert.assertTrue(errorMsg.getText().contains("There was a problem with your login"));
    }
}
>>>>>>> 6380cae (Initial commit)
