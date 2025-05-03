<<<<<<< HEAD
package com.railway.test;

import common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import pageObjects.Railway.MyTicket;

import java.util.List;

public class TC16 extends PreparationCommonTest {
    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");

        // Test data
        String selectDate = "4/23/2025";
        String selectDepartStation = "Đà Nẵng";
        String selectArriveStation = "Nha Trang";
        String selectSeatType = "Hard seat";
        String selectTicketAmount = "1";

        // Step 1: Open Home Page and Login
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Step 2: Book a Ticket
        BookTicketPage bookTicketPage = loginPage.goToBookTicket();
        bookTicketPage.bookTicketPage(selectDate, selectDepartStation, selectArriveStation, selectSeatType, selectTicketAmount);

        // Step 3: Navigate to My Ticket Page
        MyTicket myTicketPage = bookTicketPage.goToMyTicket();
        List<String> ids = myTicketPage.getIds();

        // Validate ticket IDs are not empty
        Assert.assertNotNull(ids, "Ticket IDs list is null.");
        Assert.assertFalse(ids.isEmpty(), "No tickets found to cancel.");

        // Step 4: Select a Ticket to Cancel
        String idUserSelected = myTicketPage.getAnRandomId();
        Assert.assertNotNull(idUserSelected, "No ticket ID selected.");
        Assert.assertTrue(ids.contains(idUserSelected), "Selected ticket ID is not in the list.");

        // Step 5: Cancel the Selected Ticket
        MyTicket newTicketPage = myTicketPage.cancelTicketWithId(idUserSelected);
        List<String> newIds = newTicketPage.getIds();

        // Validate the ticket is canceled
        Assert.assertFalse(newIds.contains(idUserSelected), "Ticket has not been canceled.");
        System.out.println("Ticket with ID " + idUserSelected + " has been successfully canceled.");
    }
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
>>>>>>> 6380cae (Initial commit)
