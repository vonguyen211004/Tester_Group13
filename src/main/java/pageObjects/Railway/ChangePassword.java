package pageObjects.Railway;
import org.openqa.selenium.WebDriver;
import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ChangePassword extends GeneralPage {
    public ChangePassword(WebDriver driver) {
        super(driver);  // Call the parent constructor with the driver
    }
    private final By _currentPasswordInput = By.xpath("//input[@id='currentPassword']");

    private final By _newPasswordInput = By.xpath("//input[@id='newPassword']");

    private final By _confirmPasswordInput = By.xpath("//input[@id='confirmPassword']");

    private final By _btnChangePassword = By.xpath("//input[@value='Change Password']");
    //element


    public WebElement getCurrentPassword() {
        return Constant.WEBDRIVER.findElement(_currentPasswordInput);
    }
    public WebElement getNewPassword() {
        return Constant.WEBDRIVER.findElement(_newPasswordInput);
    }
    public WebElement getConfirmPassword() {
        return Constant.WEBDRIVER.findElement(_confirmPasswordInput);
    }
    public WebElement getBtnChangePassword() {
        return Constant.WEBDRIVER.findElement(_btnChangePassword);
    }

    public ChangePassword changePassword (String currentPassword,String newPassword, String confirmPassword) {
        this.getCurrentPassword().sendKeys(currentPassword);
        this.getNewPassword().sendKeys(newPassword);
        this.getConfirmPassword().sendKeys(confirmPassword);
        this.getBtnChangePassword().click();
        return new ChangePassword(driver);
    }

}




