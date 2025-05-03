package com.railway.test;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import common.Constant.Constant;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TC13 {

    private WebDriverWait wait;

    @Test
    public void TC13() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        Constant.WEBDRIVER = new ChromeDriver(options);
        wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));

        try {
            // Pass WebDriver to the HomePage constructor
            HomePage homePage = new HomePage(Constant.WEBDRIVER);
            homePage.open();
            LoginPage loginPage = homePage.gotoLoginPage();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            WebElement forgotPasswordLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Forgot Password page"))
            );

            JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
            js.executeScript("arguments[0].scrollIntoView(true);", forgotPasswordLink);
            js.executeScript("arguments[0].click();", forgotPasswordLink);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

            WebElement emailField = Constant.WEBDRIVER.findElement(By.id("email"));
            emailField.clear();
            emailField.sendKeys(Constant.USERNAME);

            WebElement sendInstructionsButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input[type='submit'][value='Send Instructions']")
                    )
            );
            js.executeScript("arguments[0].scrollIntoView(true);", sendInstructionsButton);
            js.executeScript("arguments[0].click();", sendInstructionsButton);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPassword")));

            WebElement newPasswordField = Constant.WEBDRIVER.findElement(By.id("newPassword"));
            WebElement confirmPasswordField = Constant.WEBDRIVER.findElement(By.id("confirmPassword"));

            newPasswordField.sendKeys("NewStrongPassword123!");
            confirmPasswordField.sendKeys("DifferentPassword456!");

            WebElement resetPasswordButton = Constant.WEBDRIVER.findElement(
                    By.cssSelector("input[type='submit'][value='Reset Password']")
            );
            js.executeScript("arguments[0].click();", resetPasswordButton);

            WebElement formError = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'Could not reset password')]")
                    )
            );
            Assert.assertTrue(formError.isDisplayed());

            WebElement confirmError = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//label[@for='confirmPassword' and contains(text(),'The password confirmation did not match')]")
                    )
            );
            Assert.assertTrue(confirmError.isDisplayed());

            System.out.println("TC13 Passed");

        } catch (Exception e) {
            System.out.println("Error during TC13 execution:");
            e.printStackTrace();
            Assert.fail("TC13 failed with error: " + e.getMessage());
        } finally {
            if (Constant.WEBDRIVER != null) {
                Constant.WEBDRIVER.quit();
            }
        }
    }
}
