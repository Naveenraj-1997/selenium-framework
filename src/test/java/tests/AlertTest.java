package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertTest extends BaseTest{

    @BeforeMethod
    public void alertSetup()
    {
        driver.get(config.getAlertURL());
    }

    @Test
    public void handlesimpleAlert()
    {
        alertPage.clickjsAlert();
        Assert.assertEquals(alertPage.getAlertmsg(),"I am a JS Alert");
        alertPage.alertAccept();
        Assert.assertEquals(alertPage.getresultText(),"You successfully clicked an alert");
        System.out.println("Alert Results "+ alertPage.getresultText());
    }
    @Test
    public void handleconfirmAlert()
    {
           alertPage.clickjsconfirmAlert();
           Assert.assertEquals(alertPage.getAlertmsg(), "I am a JS Confirm");
           alertPage.alertAccept();
           Assert.assertEquals(alertPage.getresultText(), "You clicked: Ok");
           System.out.println("Alert Results " + alertPage.getresultText());

    }

    @Test
    public void handlepromptAlert()
    {
        alertPage.clickjspromptAlert();
        Assert.assertEquals(alertPage.getAlertmsg(), "I am a JS prompt");
        alertPage.sendAlerttext("Test");
        alertPage.alertAccept();
        Assert.assertEquals(alertPage.getresultText(), "You entered: Test");
        System.out.println("Alert Results " + alertPage.getresultText());
    }

}
