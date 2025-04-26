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
}