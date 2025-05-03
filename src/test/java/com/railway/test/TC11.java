package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC11 {
    WebDriver driver;

    @Test
    public void registerWithEmptyPasswordAndPid() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Register")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("emptypasspid@test.com");

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='password' and @class='validation-error']")));
        WebElement pidError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='pid' and @class='validation-error']")));

        System.out.println("Password Error: " + passwordError.getText());
        System.out.println("PID Error: " + pidError.getText());

        driver.quit();
    }
}
