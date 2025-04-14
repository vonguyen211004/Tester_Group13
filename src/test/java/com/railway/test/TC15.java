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

import static org.testng.Assert.assertEquals;

public class TC15 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Setup WebDriver và maximize cửa sổ trình duyệt
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Mở rộng trình duyệt
    }

    @Test
    public void openBookTicketFromTimetable() {
        // Navigate đến trang chính
        driver.get("http://railwayb1.somee.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Cài đặt thời gian chờ

        try {
            // Chờ và nhấn vào liên kết "Train timetable"
            WebElement trainTimetableLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Train timetable")));
            trainTimetableLink.click();

            // Chờ và nhấn vào liên kết "Book ticket"
            WebElement bookTicketLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Book ticket")));
            bookTicketLink.click();

            // Xác minh tiêu đề trang điều hướng có đúng với kỳ vọng không
            String pageTitle = driver.getTitle(); // Lấy tiêu đề trang
            assertEquals(pageTitle, "Book ticket - Safe Railway", "Page title does not match the expected value!");

            // In tiêu đề trang (chỉ để xác minh thông tin trên console)
            System.out.println("Navigated to: " + pageTitle);

        } catch (Exception e) {
            // In ra thông báo lỗi nếu có lỗi xảy ra
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