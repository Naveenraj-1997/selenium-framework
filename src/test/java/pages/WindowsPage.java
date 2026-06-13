package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowsPage {
    WebDriver driver;
    By clickherebtnele = By.linkText("Click Here");
    By newwindowheaderele = By.cssSelector("h3");

    public WindowsPage(WebDriver driver)
    {
        this.driver = driver;
    }
    public String getmainwindowHandle()
    {
        return driver.getWindowHandle();
    }
    public void switchtonewWindow(String mainwindow)
    {
        Set<String> allwindows = driver.getWindowHandles();
        for(String window:allwindows )
        {
            if(!window.equals(mainwindow))
            {
                driver.switchTo().window(window);
                break;
            }
        }
    }
    public void clicktoNewWindow()
    {
        driver.findElement(clickherebtnele).click();
    }
    public String getNewWindowHeader()
    {
        return driver.findElement(newwindowheaderele).getText();
    }
    public String getNewWidnowsTitle()
    {
        return driver.getTitle();
    }
    public void closeNewWindowandSwitchtoMain(String mainwindow)
    {
        driver.close();
        driver.switchTo().window(mainwindow);
    }

}
