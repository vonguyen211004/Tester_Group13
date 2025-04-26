package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.Constant.Constant;

public class GeneralPage {


    private  final  String commonPageTitlePath = "//div[@id='content']/h1";
    //Tab
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabTicketPrice = By.xpath("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']");
    private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private  final By tabForgotPasswordPage = By.xpath("//div[@id='content']//a[@href='/Account/ForgotPassword.cshtml']");
    private final By tabTimeTablePage = By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");

    //  Message
    private final By lblErrorMessage = By.xpath("//div[@id='content']//p[@class='message error LoginForm']");
    private final By lblErrorPassMsg = By.xpath("//div[@id='content']/p[@class='message error LoginForm']");
    private final By lblRegisterMsg = By.xpath("//div[@id='content']/p");
    private final By lblChangePasswordMsg = By.xpath("//p[@class='message success']");
    private final By lblErrorConfirmPasswordMsg = By.xpath("//p[@class='message error']");
    private final By lblErrorPasswordLengthMsg = By.xpath("//li[@class='password']/label[@class='validation-error']");
    private final By lblErrorPidLengthMsg = By.xpath("//li[@class='pid-number']/label[@class='validation-error']");
    private final By lblSuccessMessage = By.xpath("//div[@id='content']/h1");
    //Title
    private final By commonPageTitle = By.xpath(commonPageTitlePath);
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");








    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }

    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }

    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
    }

    protected WebElement getTabTicketPrice() {
        return Constant.WEBDRIVER.findElement(tabTicketPrice);
    }

    protected WebElement getTabChangePassword() {
        return Constant.WEBDRIVER.findElement(tabChangePassword);
    }
    protected WebElement getForgotPasswordPage() {
        return Constant.WEBDRIVER.findElement(tabForgotPasswordPage);
    }

    protected WebElement getTabBook() {
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }

    protected WebElement getPageTitle() {
        return Constant.WEBDRIVER.findElement(commonPageTitle);
    }
    protected WebElement getTabTimeTable() {
        return Constant.WEBDRIVER.findElement(tabTimeTablePage);
    }

    protected WebElement getLblWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }

    protected WebElement getLblErrorMassage() {
        return Constant.WEBDRIVER.findElement(lblErrorMessage);
    }

    protected WebElement getLblErrorPassMsg() {
        return Constant.WEBDRIVER.findElement(lblErrorPassMsg);
    }
    protected WebElement getLblRegisterMsg() {
        return Constant.WEBDRIVER.findElement(lblRegisterMsg);
    }
    protected WebElement getLblChangePassword() {
        return Constant.WEBDRIVER.findElement(lblChangePasswordMsg);
    }
    protected WebElement getLblErrorConfirmPasswordMsg() {
        return Constant.WEBDRIVER.findElement(lblErrorConfirmPasswordMsg);
    }
    protected WebElement getLblErrorPasswordLengthMsg() {
        return Constant.WEBDRIVER.findElement(lblErrorPasswordLengthMsg);
    }
    protected WebElement getLblErrorPidLengthMsg() {
        return Constant.WEBDRIVER.findElement(lblErrorPidLengthMsg);
    }
    protected WebElement getTabMyTicket() {
        return Constant.WEBDRIVER.findElement(tabMyTicket);
    }
    protected WebElement getLblSuccessMessage() {
        return Constant.WEBDRIVER.findElement(lblSuccessMessage);
    }

    public String getLogoutTabText() {
        return this.getTabLogout().getText();
    }
    public String getTicketPriceTabText() {
        return this.getTabTicketPrice().getText();
    }
    public String getBookTicketTabText() {
        return this.getTabBook().getText();
    }

    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }
    public String getErrorMassage() {
        return this.getLblErrorMassage().getText();
    }

    public String getErrorPassMsg() {
        return this.getLblErrorPassMsg().getText();
    }
    public String getErrorPasswordLengthMsg() {
        return this.getLblErrorPasswordLengthMsg().getText();
    }
    public String getWelcomeRegisterMsg() {
        return this.getLblRegisterMsg().getText();
    }
    public String getChangePasswordMsg() {
        return this.getLblChangePassword().getText();
    }
    public String getErrorConfirmPasswordMsg() {
        return  this.getLblErrorConfirmPasswordMsg().getText();
    }
    public String getErrorPidLengthMsg() {
        return this.getLblErrorPidLengthMsg().getText();
    }
    public String getSuccessMessage() {
        return this.getLblSuccessMessage().getText();
    }

    public  String getPageTitleText() {
        return this.getPageTitle().getText();
    }
    public String getMyTicketTabText() {
        return this.getTabMyTicket().getText();
    }




    //
    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public BookTicketPage goToBookTicket() {
        this.getTabBook().click();
        return new BookTicketPage();
    }

    public  RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }
    public ChangePassword goToChangePassWord() {
        this.getTabChangePassword().click();
        return  new ChangePassword();
    }
    public MyTicket goToMyTicket() {
        this.getTabMyTicket().click();
        return  new MyTicket();
    }
    public TimeTablePage goToTimeTablePage() {
        this.getTabTimeTable().click();
        return new TimeTablePage();
    }

    public ForgotPassword goToForgotPasswordPage() {
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", this.getForgotPasswordPage());
        this.getForgotPasswordPage().click();
        return new ForgotPassword();
    }

    public String isLoginPage() {
        return this.getPageTitle().getText();
    }




}
