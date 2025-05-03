package pageObjects.Railway;
import org.openqa.selenium.WebDriver;
import common.Constant.Constant;
public class HomePage extends GeneralPage {
    public HomePage(WebDriver driver) {
        super(driver);  // Pass WebDriver to the parent class constructor
    }
    public HomePage open(){
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }

}
