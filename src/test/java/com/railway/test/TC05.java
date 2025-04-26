package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC05 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void wrongPasswordSeveralTimes() throws InterruptedException {
        driver.get("http://railwayb1.somee.com");

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.id("username")).sendKeys("vovanbaonguyen19@gmail.com"); // Thay bằng username thật nếu có
            driver.findElement(By.id("password")).sendKeys("12346"); // Mật khẩu sai
            driver.findElement(By.cssSelector("input[type='submit']")).click();

            // Tạm dừng 1 giây để hệ thống xử lý
            Thread.sleep(1000);
        }

        // Sau khi sai 5 lần, hệ thống có thể báo lỗi
        WebElement errorMsg = driver.findElement(By.cssSelector(".message.error.LoginForm"));
        String actualText = errorMsg.getText();
        System.out.println("Error Message: " + actualText);

        // Kiểm tra nội dung thông báo lỗi
        Assert.assertTrue(actualText.contains("You have used 4 out of 5 login attempts") ||
                        actualText.contains("Invalid username or password. Please try again."));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
