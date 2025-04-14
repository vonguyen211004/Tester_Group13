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
        driver.findElement(By.id("password")).sendKeys("anyPassword");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebElement errorMsg = driver.findElement(By.cssSelector(".message.error"));
        Assert.assertTrue(errorMsg.getText().contains("There was a problem with your login"));
    }
}
