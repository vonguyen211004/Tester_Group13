package com.railway.test;

import common.Constant.Constant;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import org.openqa.selenium.*;

import java.util.Map;

public class TC14 extends PreparationCommonTest {

    @Test
    public void TC14() {
        SoftAssert softAssert = new SoftAssert();

        String expectedDate = "5/11/2025";
        String expectedDepartStation = "Sài Gòn";
        String expectedArriveStation = "Nha Trang";
        String expectedSeatType = "Soft bed with air conditioner";
        String expectedTicketAmount = "1";
        String expectedUrlBase = "http://railwayb1.somee.com/Page/SuccessPage";

        HomePage homePage = new HomePage(Constant.WEBDRIVER).open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = loginPage.goToBookTicket();

        try {
            String actualSuccessMessage = bookTicketPage.bookTicketPage(expectedDate, expectedDepartStation, expectedArriveStation, expectedSeatType, expectedTicketAmount).getSuccessMessage();
            String expectedSuccessMessage = "Ticket booked successfully!";

            softAssert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "TEST FAIL: Success message is not displayed as expected.");

            String actualUrl = bookTicketPage.getCurrentUrl();
            softAssert.assertTrue(actualUrl.contains(expectedUrlBase), "TEST FAIL: The page was not redirected to the success page. Actual URL: " + actualUrl);

            Map<String, String> ticketInformation = bookTicketPage.getTicketInformation();
            if (ticketInformation != null && !ticketInformation.isEmpty()) {
                softAssert.assertEquals(ticketInformation.get("Depart Date"), expectedDate, "TEST FAIL: Depart Date is incorrect.");
                softAssert.assertEquals(ticketInformation.get("Depart Station"), expectedDepartStation, "TEST FAIL: Depart Station is incorrect.");
                softAssert.assertEquals(ticketInformation.get("Arrive Station"), expectedArriveStation, "TEST FAIL: Arrive Station is incorrect.");
                softAssert.assertEquals(ticketInformation.get("Seat Type"), expectedSeatType, "TEST FAIL: Seat Type is incorrect.");
                softAssert.assertEquals(ticketInformation.get("Amount"), expectedTicketAmount, "TEST FAIL: Ticket Amount is incorrect.");
            } else {
                softAssert.fail("TEST FAIL: Ticket information is null or empty.");
            }

        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for the element to be visible: " + e.getMessage());
            softAssert.fail("Timeout waiting for the element to be visible: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            softAssert.fail("Element not found: " + e.getMessage());
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is stale: " + e.getMessage());
            softAssert.fail("Element is stale: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            softAssert.fail("Unexpected error: " + e.getMessage());
        } finally {
            softAssert.assertAll();
        }
    }
}
