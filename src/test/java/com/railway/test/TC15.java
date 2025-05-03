<<<<<<< HEAD
package com.railway.test;

import common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import pageObjects.Railway.TimeTablePage;

public class TC15 extends PreparationCommonTest {
    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        // Step 1: Open Home Page
        HomePage homePage = new HomePage().open();

        // Step 2: Navigate to Login Page and Login
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Step 3: Access TimeTable Page
        TimeTablePage timeTablePage = loginPage.goToTimeTablePage();

        // Step 4: Click on 'Book Ticket' Link
        System.out.println("Clicking 'Book Ticket' link for stations Huế to Sài Gòn...");
        timeTablePage.clickBookTicketLink("Huế", "Sài Gòn");

        // Step 5: Validate Arrival and Departure Values on Book Ticket Page
        BookTicketPage bookTicketPage = new BookTicketPage();

        System.out.println("Validating departure and arrival values...");
        boolean isDepartCorrect = bookTicketPage.isDepartValuesCorrect("Huế");
        boolean isArriveCorrect = bookTicketPage.isArriveValuesCorrect("Sài Gòn");

        // Debugging logs
        System.out.println("Departure validation result: " + isDepartCorrect);
        System.out.println("Arrival validation result: " + isArriveCorrect);

        Assert.assertTrue(isDepartCorrect, "Depart from is not correct.");
        Assert.assertTrue(isArriveCorrect, "Arrive at is not correct.");
    }
=======
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
>>>>>>> 6380cae (Initial commit)
}