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