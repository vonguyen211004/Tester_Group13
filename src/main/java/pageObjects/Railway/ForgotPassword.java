package pageObjects.Railway;

import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPassword {
    private final By _txtEmailAddress = By.xpath("//input[@id='email']");
    private final By _btnSendInstruction = By.xpath("//input[@value='Send Instructions']");


    public WebElement getTxtEmailAddress() {
        return Constant.WEBDRIVER.findElement(_txtEmailAddress);
    }
    public WebElement getSendInstruction() {
        return Constant.WEBDRIVER.findElement(_btnSendInstruction);
    }

    public ForgotPassword forgotPassword(String email)
    {
        this.getTxtEmailAddress().sendKeys(email);
        this.getSendInstruction().click();
        return new ForgotPassword();
    }
}
