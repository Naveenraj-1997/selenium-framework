package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {


    @DataProvider(name ="excellogindata")
    public Object[][] getExcelLoginData()
    {
        return ExcelUtils.getExcelData("src/test/resources/testdata.xlsx","LoginData");
    }
    @Test(priority = 1, groups = {"valid"}, enabled = true)
    public void validlogintest() throws InterruptedException {
        loginPage.login(config.getUsername(), config.getPassword());
        Thread.sleep(2000);
        Assert.assertEquals(getDriver().getTitle(), "Swag Labs");

    }

    @Test(priority = 2,groups = {"invalid"},enabled = true , dependsOnMethods = {"validlogintest"})
    public void invalidLogintest() throws InterruptedException {
        loginPage.login(config.getUsername(), "Test");
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
    @Test(priority = 3,groups = {"invalid"}, dataProvider = "excellogindata", enabled = false)
    public void loginTestFromExcel(String tcNo,
                                   String testCaseName,
                                   String username,
                                   String password,
                                   Boolean expectedResult)
    {
        loginPage.login(username,password);
        if(expectedResult)
        {
            Assert.assertEquals(getDriver().getTitle(), "Swag Labs");
            System.out.println(tcNo +" Valid login test " + testCaseName);
        }else{
            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
            System.out.println(tcNo +" Invalid login passed: " + testCaseName);
        }
    }
}

