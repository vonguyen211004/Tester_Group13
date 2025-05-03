<<<<<<< HEAD
package com.railway.test;

import common.Constant.Constant;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;

import java.util.Map;

public class TC14 extends PreparationCommonTest {

    @Test
    public void TC14() {
        SoftAssert softAssert = new SoftAssert();

        // Test data
        String expectedDate = "4/23/2025";
        String expectedDepartStation = "Đà Nẵng";
        String expectedArriveStation = "Nha Trang";
        String expectedSeatType = "Hard seat";
        String expectedTicketAmount = "1";

        // Step 1: Open Home Page and Login
        HomePage homePage = new HomePage().open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        // Step 2: Navigate to Book Ticket Page and Book a Ticket
        BookTicketPage bookTicketPage = loginPage.goToBookTicket();
        String actualSuccessMessage = bookTicketPage.bookTicketPage(expectedDate, expectedDepartStation, expectedArriveStation, expectedSeatType, expectedTicketAmount).getSuccessMessage();
        String expectedSuccessMessage = "Ticket booked successfully!";

        // Step 3: Validate Success Message
        softAssert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "TEST FAIL: Success message is not displayed as expected.");

        // Step 4: Validate Ticket Information
        Map<String, String> ticketInformation = bookTicketPage.getTicketInformation();
        if (ticketInformation != null) {
            softAssert.assertEquals(ticketInformation.get("Depart Date"), expectedDate, "TEST FAIL: Depart Date is incorrect.");
            softAssert.assertEquals(ticketInformation.get("Depart Station"), expectedDepartStation, "TEST FAIL: Depart Station is incorrect.");
            softAssert.assertEquals(ticketInformation.get("Arrive Station"), expectedArriveStation, "TEST FAIL: Arrive Station is incorrect.");
            softAssert.assertEquals(ticketInformation.get("Seat Type"), expectedSeatType, "TEST FAIL: Seat Type is incorrect.");
            softAssert.assertEquals(ticketInformation.get("Amount"), expectedTicketAmount, "TEST FAIL: Ticket Amount is incorrect.");
        } else {
            softAssert.fail("TEST FAIL: Ticket information is null.");
        }

        // Assert all
        softAssert.assertAll();
    }
}
=======
package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC14 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void bookOneTicket() {
        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validEmail");
        driver.findElement(By.id("password")).sendKeys("validPassword");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        driver.findElement(By.linkText("Book ticket")).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String message = driver.findElement(By.xpath("//div[@id='content']")).getText();
        System.out.println("Booking Result: " + message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
>>>>>>> 6380cae (Initial commit)
