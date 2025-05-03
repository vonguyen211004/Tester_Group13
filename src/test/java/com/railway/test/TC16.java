package com.railway.test;

import common.Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import pageObjects.Railway.BookTicketPage;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import pageObjects.Railway.MyTicket;

import java.util.List;

public class TC16 extends PreparationCommonTest {
    @Test
    public void testUserCanCancelTicket() {
        System.out.println("TC16 - User can cancel a ticket");

        String date = "5/17/2025";
        String departStation = "Quảng Ngãi";
        String arriveStation = "Nha Trang";
        String seatType = "Hard seat";
        String ticketAmount = "1";

        HomePage homePage = new HomePage(Constant.WEBDRIVER).open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = loginPage.goToBookTicket();

        try {
            handleAlert();
            bookTicketPage.bookTicketPage(date, departStation, arriveStation, seatType, ticketAmount);
        } catch (Exception e) {
            System.out.println("Exception while booking ticket: " + e.getMessage());
            Assert.fail("Booking ticket failed: " + e.getMessage());
        }

        MyTicket myTicketPage = bookTicketPage.goToMyTicket();
        List<String> ticketIds = myTicketPage.getIds();

        Assert.assertNotNull(ticketIds, "Ticket IDs list is null.");
        Assert.assertFalse(ticketIds.isEmpty(), "No tickets found to cancel.");

        String selectedId = myTicketPage.getAnRandomId();
        Assert.assertNotNull(selectedId, "No ticket ID selected.");
        Assert.assertTrue(ticketIds.contains(selectedId), "Selected ticket ID is not in the list.");

        myTicketPage.cancelTicketWithId(selectedId);
        List<String> updatedIds = myTicketPage.getIds();

        Assert.assertFalse(updatedIds.contains(selectedId), "Ticket has not been canceled.");
        System.out.println("Ticket with ID " + selectedId + " has been successfully canceled.");
    }

    private void handleAlert() {
        try {
            Alert alert = Constant.WEBDRIVER.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert found with text: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        }
    }
}
