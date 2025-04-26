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
    public void invalidPasswordLogin() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validUsername");
        driver.findElement(By.id("password")).sendKeys("wrongPassword");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.error")));
        
        String actualErrorMsg = errorMsg.getText();
        String expectedErrorMsg = "Invalid username or password. Please try again."; // Cập nhật chuỗi này theo thông báo thực tế
        Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
                "Thông báo lỗi không đúng! Thực tế: " + actualErrorMsg);
    }
}
