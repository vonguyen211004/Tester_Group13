package com.railway.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.LoginPage;
import common.Constant.Constant;

public class TC05 {

    @Test
    public void TC05() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Constant.WEBDRIVER = driver;

        try {
            HomePage homePage = new HomePage(Constant.WEBDRIVER).open();
            LoginPage loginPage = homePage.gotoLoginPage();

            for (int i = 1; i <= 4; i++) {
                loginPage.getTxtUsername().clear();
                loginPage.getTxtPassword().clear();

                loginPage.getTxtUsername().sendKeys(Constant.USERNAME);
                loginPage.getTxtPassword().sendKeys("invalid_password");
                loginPage.getBtnLogin().click();

                if (i == 4) {
                    String actualMsg = loginPage.getLoginErrorMsg().trim();
                    String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
                    Assert.assertEquals(actualMsg, expectedMsg, "Error message after 4 failed login attempts is incorrect.");
                }
            }
        } finally {
            driver.quit();
        }
    }
}
