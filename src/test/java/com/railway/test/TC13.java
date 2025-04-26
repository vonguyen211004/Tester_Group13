package com.railway.test;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
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

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        Constant.WEBDRIVER = new ChromeDriver(options);
        wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(20));
    }

    @Test
    public void TC13() {
        System.out.println("TC13 - Open Reset Password Page and Wait 5 seconds to Pass");

        try {
            HomePage homePage = new HomePage();
            homePage.open();
            LoginPage loginPage = homePage.gotoLoginPage();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

            WebElement forgotPasswordLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Forgot Password page"))
            );

            JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
            js.executeScript("arguments[0].scrollIntoView(true);", forgotPasswordLink);
            js.executeScript("arguments[0].click();", forgotPasswordLink);
            System.out.println("Đã điều hướng đến trang Forgot Password");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

            WebElement emailField = Constant.WEBDRIVER.findElement(By.id("email"));
            emailField.clear();
            emailField.sendKeys(Constant.USERNAME);
            System.out.println("Email đã nhập: " + Constant.USERNAME);

            WebElement sendInstructionsButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input[type='submit'][value='Send Instructions']")
                    )
            );
            js.executeScript("arguments[0].scrollIntoView(true);", sendInstructionsButton);
            js.executeScript("arguments[0].click();", sendInstructionsButton);
            System.out.println("Đã nhấp vào nút Send Instructions");

            // CHỈ KIỂM TRA MỞ ĐƯỢC TRANG RESET PASSWORD THÀNH CÔNG
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPassword")));
            System.out.println("Trang Reset Password đã mở thành công");

            // Đợi 5 giây để giả lập gửi mail và mở form thành công
            Thread.sleep(2000);

            // Nếu không lỗi gì thì Passed
            System.out.println("TC13 Passed - Đã mở trang Reset Password thành công và chờ 5 giây");

        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình thực thi TC13:");
            e.printStackTrace();
            Assert.fail("TC13 thất bại với lỗi: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
            System.out.println("Trình duyệt đã đóng");
        }
    }
}
