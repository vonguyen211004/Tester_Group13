package pageObjects.Railway;
import org.openqa.selenium.WebDriver;
import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;

public class BookTicketPage extends GeneralPage {
    private WebDriver driver;
    public BookTicketPage(WebDriver driver) {
        super(driver);  // Pass WebDriver to the parent class constructor (GeneralPage)
    }
    private final By datePath = By.xpath("//select[@name='Date']");
    private final By departStation = By.xpath("//select[@name='DepartStation']");
    private final By arriveStation = By.xpath("//select[@name='ArriveStation']");
    private final By seatType = By.xpath("//select[@name='SeatType']");
    private final By ticketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");
    private final By ticketInformation = By.xpath("//tr[@class='OddRow']//td");
    private final By ticketInformationTitles = By.xpath("//tr[@class='TableSmallHeader']//th");
    private final By inForDepartStation = By.xpath("//select[@name='DepartStation']/option[@selected='selected']");
    private final By inForArriveStation = By.xpath("//select[@name='ArriveStation']/option[@selected='selected']");

    protected WebElement getDateSelectBox() {
        return Constant.WEBDRIVER.findElement(datePath);
    }

    protected WebElement getDepartStation() {
        return Constant.WEBDRIVER.findElement(departStation);
    }

    protected WebElement getArriveStation() {
        return Constant.WEBDRIVER.findElement(arriveStation);
    }

    protected WebElement getSeatType() {
        return Constant.WEBDRIVER.findElement(seatType);
    }

    protected WebElement getTicketAmount() {
        return Constant.WEBDRIVER.findElement(ticketAmount);
    }

    protected WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(btnBookTicket);
    }

    protected WebElement getInForDepartStation() {
        return Constant.WEBDRIVER.findElement(inForDepartStation);
    }

    protected WebElement getInForArriveStation() {
        return Constant.WEBDRIVER.findElement(inForArriveStation);
    }

    public void selectDate(String date) {
        WebElement dateSelectBox = getDateSelectBox();
        Select select = new Select(dateSelectBox);
        select.selectByVisibleText(date);
    }

    public void selectDepartStation(String departStation) {
        WebElement departStationBox = getDepartStation();
        Select select = new Select(departStationBox);
        select.selectByVisibleText(departStation);
    }

    public void selectArriveStation(String arriveStation) {
        WebElement arriveStationBox = getArriveStation();
        Select select = new Select(arriveStationBox);
        select.selectByVisibleText(arriveStation);
    }

    public void selectSeatType(String seatStyle) {
        WebElement seatStyleBox = getSeatType();
        Select select = new Select(seatStyleBox);
        select.selectByVisibleText(seatStyle);
    }

    public void selectTicketAmount(String ticketAmount) {
        WebElement ticketAmountBox = getTicketAmount();
        Select select = new Select(ticketAmountBox);
        select.selectByVisibleText(ticketAmount);
    }

    public Boolean isDepartValuesCorrect(String value) {
        return getInForDepartStation().getText().equals(value);
    }

    public Boolean isArriveValuesCorrect(String value) {
        return getInForArriveStation().getText().equals(value);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public BookTicketPage bookTicketPage(String date, String departStation, String arriveStation, String seatType, String ticketAmount) {
        this.selectDate(date);
        this.selectDepartStation(departStation);
        this.selectArriveStation(arriveStation);
        this.selectSeatType(seatType);
        this.selectTicketAmount(ticketAmount);

        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", this.getBtnBookTicket());

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(this.getBtnBookTicket()));

        this.getBtnBookTicket().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someElementOnTheNextPage")));

        return new BookTicketPage(driver);
    }

    public Map<String, String> getTicketInformation() {
        Map<String, String> information = new HashMap<>();

        List<WebElement> ticketInformationTitle = Constant.WEBDRIVER.findElements(ticketInformationTitles);
        List<WebElement> ticketInformationValue = Constant.WEBDRIVER.findElements(ticketInformation);

        if (ticketInformationTitle != null && ticketInformationValue != null && ticketInformationTitle.size() == ticketInformationValue.size()) {
            for (int i = 0; i < ticketInformationTitle.size(); i++) {
                information.put(ticketInformationTitle.get(i).getText(), ticketInformationValue.get(i).getText());
            }
        } else {
            throw new NoSuchElementException("Ticket information not found.");
        }

        return information;
    }

    public boolean isPageDisplayed() {
        return Constant.WEBDRIVER.getTitle().equals("Safe Railway - Book Ticket");
    }
}
