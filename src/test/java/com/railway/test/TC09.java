<<<<<<< HEAD
package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC09 {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    private static final String URL = "http://railwayb1.somee.com/";
    private static final String USERNAME = System.getenv("RAILWAY_USERNAME") != null
            ? System.getenv("RAILWAY_USERNAME")
            : "vovanbaonguyen19@gmail.com";
    private static final String PASSWORD = System.getenv("RAILWAY_PASSWORD") != null
            ? System.getenv("RAILWAY_PASSWORD")
            : "baonguyen2004";
    private final String newPassword = "thuthao123";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void TC09_NguoiDungCoTheThayDoiMatKhau() {
        try {
            // Step 1: Truy cập trang web
            openUrl(URL);

            // Step 2: Đăng nhập
            login(USERNAME, PASSWORD);

            // Step 3: Đổi mật khẩu
            changePassword(PASSWORD, newPassword);

            // Step 4: Kiểm tra thông báo
            verifyMessage("//p[@class='message success']", "Your password has been updated!");

            // Step 5: Khôi phục lại mật khẩu ban đầu
            changePassword(newPassword, PASSWORD);
            System.out.println("Khôi phục mật khẩu ban đầu thành công.");

        } catch (Exception e) {
            logError("Lỗi trong quá trình thực thi TC09", e);
            Assert.fail("TC09 thất bại: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Đóng trình duyệt...");
            driver.quit();
        }
    }

    private void openUrl(String url) {
        try {
            driver.get(url);
            System.out.println("Truy cập URL thành công: " + url);
        } catch (Exception e) {
            throw new RuntimeException("Không thể mở URL: " + url, e);
        }
    }

    private void login(String username, String password) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login"))).click();
            System.out.println("Đi đến trang Login...");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.xpath("//input[@type='submit']")).click();

            System.out.println("Đăng nhập thành công với: " + username);
        } catch (Exception e) {
            throw new RuntimeException("Không thể đăng nhập!", e);
        }
    }

    private void changePassword(String currentPassword, String newPassword) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Change password"))).click();
            System.out.println("Đi đến trang Change Password...");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentPassword"))).sendKeys(currentPassword);
            driver.findElement(By.id("newPassword")).sendKeys(newPassword);
            driver.findElement(By.id("confirmPassword")).sendKeys(newPassword);

            WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
            js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
            js.executeScript("arguments[0].click();", submitBtn);

            System.out.println("Đổi mật khẩu từ '" + currentPassword + "' sang '" + newPassword + "' thành công.");
        } catch (Exception e) {
            throw new RuntimeException("Không thể thay đổi mật khẩu!", e);
        }
    }

    private void verifyMessage(String xpath, String expectedMessage) {
        try {
            String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
            System.out.println("Thông báo hiển thị: " + actualMessage);
            Assert.assertEquals(actualMessage, expectedMessage, "Thông báo không khớp!");
        } catch (Exception e) {
            throw new RuntimeException("Không thể kiểm tra thông báo!", e);
        }
    }

    private void logError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
    }
}
=======
package com.railway.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC09 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void changePasswordSuccessfully() {
        driver.get("http://railwayb1.somee.com/");

        // Step 1: Login
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("username")).sendKeys("validUser@test.com"); // hãy chắc chắn là user này đã tồn tại
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // Step 2: Check login success
        try {
            WebElement welcomeMsg = driver.findElement(By.xpath("//div[@class='account']/strong"));
            System.out.println("Login success: " + welcomeMsg.getText());
        } catch (NoSuchElementException e) {
            System.out.println("Login failed. Check credentials.");
            return;
        }

        // Step 3: Click Change password
        try {
            driver.findElement(By.linkText("Change password")).click();

            driver.findElement(By.id("currentPassword")).sendKeys("12345678");
            driver.findElement(By.id("newPassword")).sendKeys("newpass123");
            driver.findElement(By.id("confirmPassword")).sendKeys("newpass123");
            driver.findElement(By.xpath("//input[@type='submit']")).click();

            // Step 4: Verify success message
            String message = driver.findElement(By.xpath("//p[@class='message success']")).getText();
            System.out.println("Change password success message: " + message);
        } catch (NoSuchElementException e) {
            System.out.println("Link 'Change password' not found. Are you logged in?");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
>>>>>>> 6380cae (Initial commit)
