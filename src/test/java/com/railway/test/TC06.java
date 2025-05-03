package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC06 {
    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void checkPagesAfterLogin() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://railwayb1.somee.com");

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();

        driver.findElement(By.id("username")).sendKeys("vovanbaonguyen19@gmail.com");
        driver.findElement(By.id("password")).sendKeys("baonguyen2004");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My ticket")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Change password")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

        assertTrue(driver.findElement(By.linkText("My ticket")).isDisplayed());
        assertTrue(driver.findElement(By.linkText("Change password")).isDisplayed());
        assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed());

        driver.findElement(By.linkText("My ticket")).click();
        wait.until(ExpectedConditions.urlContains("ManageTicket"));

        driver.findElement(By.linkText("Change password")).click();
        wait.until(ExpectedConditions.urlContains("ChangePassword"));

        driver.quit();
    }
}
