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

        HomePage homePage = new HomePage(Constant.WEBDRIVER).open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        TimeTablePage timeTablePage = loginPage.goToTimeTablePage();

        timeTablePage.clickBookTicketLink("Huế", "Sài Gòn");

        BookTicketPage bookTicketPage = new BookTicketPage(Constant.WEBDRIVER);

        System.out.println("Validating departure and arrival values...");
        boolean isDepartCorrect = bookTicketPage.isDepartValuesCorrect("Huế");
        boolean isArriveCorrect = bookTicketPage.isArriveValuesCorrect("Sài Gòn");

        System.out.println("Departure validation result: " + isDepartCorrect);
        System.out.println("Arrival validation result: " + isArriveCorrect);

        Assert.assertTrue(isDepartCorrect, "Depart from is not correct.");
        Assert.assertTrue(isArriveCorrect, "Arrive at is not correct.");
    }
}
