package pageObjects.Railway;

import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class RegisterPage extends GeneralPage{
    //locator
    private final By _usernameInput = By.xpath("//input[@id='email']");
    private final By _passwordInput = By.xpath("//input[@id='password']");
    private final By _confirmPasswordInput = By.xpath("//input[@id='confirmPassword']");
    private final By _pidInput = By.xpath("//input[@id='pid']");
    private final By _btnRegister = By.xpath("//input[@value='Register']");

    //element
    public WebElement getUsernameInput() {
        return Constant.WEBDRIVER.findElement(_usernameInput);
    }
    public WebElement getPasswordInput() {
        return Constant.WEBDRIVER.findElement(_passwordInput);
    }
    public WebElement getConfirmPasswordInput() {
        return Constant.WEBDRIVER.findElement(_confirmPasswordInput);
    }
    public WebElement getPidInput() {
        return Constant.WEBDRIVER.findElement(_pidInput);
    }
    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }
    public RegisterPage register(String username, String password, String confirmPassword, String pid) {
        this.getUsernameInput().sendKeys(username);
        this.getPasswordInput().sendKeys(password);
        this.getConfirmPasswordInput().sendKeys(confirmPassword);
        this.getPidInput().sendKeys(pid);
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", this.getBtnRegister());
        this.getBtnRegister().click();
        return new RegisterPage();
    }

}
