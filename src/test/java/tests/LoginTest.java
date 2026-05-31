package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validlogintest() throws InterruptedException {
        loginPage.login(config.getUsername(), config.getPassword());
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Swag Labs");

    }

    @Test
    public void invalidLogintest() throws InterruptedException {
        loginPage.login(config.getUsername(), "Test");
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
}

