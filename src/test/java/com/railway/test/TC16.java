package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import static org.testng.Assert.assertFalse;

public class TC16 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup WebDriver và maximize cửa sổ trình duyệt
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void cancelTicket() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Navigate đến trang chính
            driver.get("http://railwayb1.somee.com/");

            // Nhấn vào liên kết "Login"
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login")));
            loginLink.click();

            // Điền tài khoản đăng nhập
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("validEmail@example.com"); // Thay bằng email hợp lệ

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("validPassword123"); // Thay bằng mật khẩu hợp lệ

            WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
            loginButton.click();

            // Đi đến trang "My ticket"
            WebElement myTicketLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("My ticket")));
            myTicketLink.click();

            // Hủy vé
            WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Cancel']")));
            cancelButton.click();

            // Chấp nhận cảnh báo hủy vé
            driver.switchTo().alert().accept();

            // Xác minh rằng vé đã được xóa khỏi danh sách
            WebElement ticketTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
            String updatedContent = ticketTable.getText();

            System.out.println("Ticket list after cancel: " + updatedContent);

            // Assert rằng vé đã bị hủy
            assertFalse(updatedContent.contains("Ticket ID"), "Ticket was not removed from the list!");

        } catch (Exception e) {
            // In thông báo lỗi nếu xảy ra exception
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        // Đóng trình duyệt
        if (driver != null) {
            driver.quit();
        }
    }
}
