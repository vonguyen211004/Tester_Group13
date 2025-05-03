<<<<<<< HEAD
package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC07 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void createAccountSuccessfully() {
        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Register")).click();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("confirmPassword")).sendKeys("12345678");
        driver.findElement(By.id("pid")).sendKeys("123456789");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String message = driver.findElement(By.xpath("//div[@id='content']")).getText();
        System.out.println("Register message: " + message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
=======
package com.railway.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC07 {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void createAccountSuccessfully() {
        driver.get("http://railwayb1.somee.com/");
        driver.findElement(By.linkText("Register")).click();

        String email = "user" + System.currentTimeMillis() + "@test.com";
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("confirmPassword")).sendKeys("12345678");
        driver.findElement(By.id("pid")).sendKeys("123456789");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String message = driver.findElement(By.xpath("//div[@id='content']")).getText();
        System.out.println("Register message: " + message);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
>>>>>>> 6380cae (Initial commit)
