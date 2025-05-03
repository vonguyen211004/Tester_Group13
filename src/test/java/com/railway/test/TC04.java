<<<<<<< HEAD
package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04 extends baseTest {

    @Test
    public void bookTicketWithoutLogin() {
        driver.findElement(By.linkText("Book ticket")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("Login"));
    }
}
=======
package com.railway.test;

import com.railway.base.baseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04 extends baseTest {

    @Test
    public void bookTicketWithoutLogin() {
        driver.findElement(By.linkText("Book ticket")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("Login"));
    }
}
>>>>>>> 6380cae (Initial commit)
