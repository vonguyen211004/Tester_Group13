package pageObjects.Railway;

import common.Constant.Constant;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyTicket extends GeneralPage{
    private final By _btnMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");

    // Tìm tất cả các thẻ <input> có giá trị "Cancel"
    private  final By cancelButtonElements = By.xpath("//input[@value='Cancel']");
    public WebElement getBtnMyTicket() {
        return Constant.WEBDRIVER.findElement(_btnMyTicket);
    }

    public  List<WebElement> getCancelButtons () {
        return Constant.WEBDRIVER.findElements(cancelButtonElements);
    }

    public MyTicket myTicket()
    {
        this.getBtnMyTicket().click();
        return new MyTicket();
    }

    public List<String> getIds() {
        List<WebElement> cancelButtons = getCancelButtons();
        List<String> ids = new ArrayList<>();
        for (WebElement button : cancelButtons) {
            String onClickValue = button.getAttribute("onclick");
            if(onClickValue !=null) {
                String id = onClickValue.replaceAll("\\D+", ""); // Loại bỏ tất cả các ký tự không phải số
                ids.add(id);
            }
        }
        return ids;
    }

    // Mô phỏng việc user chọn một vẽ ngẫu nhiên

    public String getAnRandomId() {
        Random random = new Random();
        List<String> ids = getIds();
        // Lấy một chỉ số ngẫu nhiên trong phạm vi kích thước của danh sách
        int randomIndex = random.nextInt(ids.size());
        return ids.get(randomIndex);
    }

    public  MyTicket cancelTicketWithId(String id) {
        WebElement cancelButton = Constant.WEBDRIVER.findElement(By.xpath("//input[@value='Cancel' and contains(@onclick, 'DeleteTicket(" + id + ")')]"));
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);",cancelButton);
        cancelButton.click();
        Alert alert = Constant.WEBDRIVER.switchTo().alert();
        alert.accept();
        return  new MyTicket();
    }
}
