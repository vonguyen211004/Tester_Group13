package com.railway.test;

import common.Constant.Constant;
import org.testng.annotations.Test;
import pageObjects.Railway.HomePage;
import pageObjects.Railway.RegisterPage;

public class TC07 extends PreparationCommonTest {

    @Test
    public void TC07_UserCanRegisterSuccessfully() {
        HomePage homePage = new HomePage(Constant.WEBDRIVER).open();
        RegisterPage registerPage = homePage.gotoRegisterPage();

        String uniqueEmail = "vovanbaonguyen19" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register(uniqueEmail, "baonguyen123", "123456789", "123456789");
        
    }
}
