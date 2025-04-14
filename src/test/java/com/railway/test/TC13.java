package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TC13 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup WebDriver và maximize cửa sổ trình duyệt
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void resetPasswordMismatch() {
        driver.get("http://railwayb1.somee.com/Account/ForgotPassword.cshtml");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Đảm bảo trang đã được tải đầy đủ
        boolean isPageLoaded = (Boolean) js.executeScript("return document.readyState === 'complete';");
        if (!isPageLoaded) {
            System.out.println("Page is not fully loaded. Test aborted.");
            return;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Điền vào token
            WebElement tokenField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("token")));
            tokenField.sendKeys("someToken123"); // Token hợp lệ

            // Điền mật khẩu mới
            WebElement newPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newPassword")));
            newPasswordField.sendKeys("12345678");

            // Điền xác nhận mật khẩu không khớp
            WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmPassword")));
            confirmPasswordField.sendKeys("87654321");

            // Scroll và nhấn nút submit
            WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
            js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
            js.executeScript("arguments[0].click();", submitButton);

            // Chờ thông báo lỗi và lấy thông báo
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='message error']")));
            String error = errorMessage.getText();

            // Xác minh thông báo lỗi chính xác
            assertEquals(error, "Passwords do not match.", "Error message content is incorrect!");
            System.out.println("Reset error: " + error);

        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        // Đóng trình duyệt sau khi test hoàn tất
        if (driver != null) {
            driver.quit();
        }
    }
}