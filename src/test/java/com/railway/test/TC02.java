package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02 extends baseTest {

    @Test
    public void blankUsernameLogin() {
        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("password")).sendKeys("baonguyen");

        driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".message.error"));
        String actualErrorMsg = errorMsg.getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
                "Incorrect error message! Actual result: " + actualErrorMsg);
    }
}
