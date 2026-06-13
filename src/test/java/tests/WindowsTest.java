package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowsTest extends BaseTest{

    @BeforeMethod
    public void windowSetup()
    {
        driver.get(config.getWindowURL());
    }
    @Test
    public void verifyNewWindow()
    {
        String mainwindow = windowspage.getmainwindowHandle();
        System.out.println("mainwindow "+ mainwindow);

        windowspage.clicktoNewWindow();
        windowspage.switchtonewWindow(mainwindow);
        System.out.println("Switched to new window");
        Assert.assertEquals(windowspage.getNewWindowHeader(),"New Window");
        windowspage.closeNewWindowandSwitchtoMain(mainwindow);
        Assert.assertEquals(windowspage.getNewWidnowsTitle(), "The Internet");
    }
}
