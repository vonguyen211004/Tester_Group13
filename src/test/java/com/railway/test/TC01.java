package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01 extends baseTest {

    @Test
    public void validLogin() {
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validUsername");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        WebElement welcomeMsg = driver.findElement(By.cssSelector(".account > strong"));
        Assert.assertTrue(welcomeMsg.getText().contains("Welcome"));
    }
}
