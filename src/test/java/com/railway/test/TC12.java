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

public class TC12 {

    private WebDriverWait wait;

    @Test
    public void TC12() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        Constant.WEBDRIVER = new ChromeDriver(options);
        wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;

        try {
            HomePage homePage = new HomePage(Constant.WEBDRIVER);
            homePage.open();
            LoginPage loginPage = homePage.gotoLoginPage();

            WebElement forgotPasswordLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Forgot Password page"))
            );
            js.executeScript("arguments[0].scrollIntoView(true);", forgotPasswordLink);
            js.executeScript("arguments[0].click();", forgotPasswordLink);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            WebElement emailField = Constant.WEBDRIVER.findElement(By.id("email"));
            emailField.clear();
            emailField.sendKeys(Constant.USERNAME);

            WebElement sendInstructionsButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit'][value='Send Instructions']"))
            );
            js.executeScript("arguments[0].scrollIntoView(true);", sendInstructionsButton);
            js.executeScript("arguments[0].click();", sendInstructionsButton);

            Constant.WEBDRIVER.get("https://qa.railway.app/Account/ResetPassword?token=someValidToken");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPassword")));

            WebElement newPassword = Constant.WEBDRIVER.findElement(By.id("newPassword"));
            WebElement confirmPassword = Constant.WEBDRIVER.findElement(By.id("confirmPassword"));
            WebElement resetToken = Constant.WEBDRIVER.findElement(By.id("resetToken"));

            newPassword.clear();
            newPassword.sendKeys("NewPassword123");

            confirmPassword.clear();
            confirmPassword.sendKeys("NewPassword123");

            resetToken.clear();

            WebElement resetPasswordBtn = Constant.WEBDRIVER.findElement(By.cssSelector("input[type='submit'][value='Reset Password']"));
            js.executeScript("arguments[0].scrollIntoView(true);", resetPasswordBtn);
            js.executeScript("arguments[0].click();", resetPasswordBtn);

            WebElement formErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'The password reset token is incorrect') or contains(text(),'may be expired')]")
            ));
            Assert.assertTrue(formErrorMessage.isDisplayed(), "Form error message is not displayed.");

            WebElement tokenFieldError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'The password reset token is invalid')]")
            ));
            Assert.assertTrue(tokenFieldError.isDisplayed(), "Token field error message is not displayed.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case TC12 failed with error: " + e.getMessage());
        } finally {
            if (Constant.WEBDRIVER != null) {
                Constant.WEBDRIVER.quit();
            }
        }
    }
}
