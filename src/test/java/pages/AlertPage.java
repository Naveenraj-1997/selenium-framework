package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertPage
{
    WebDriver driver;
    By jsAlertEle = By.xpath("//button[text() ='Click for JS Alert']");
    By jsconfirmAlertEle = By.xpath("//button[text() ='Click for JS Confirm']");
    By jspromptAlertEle =  By.xpath("//button[text()='Click for JS Prompt']");
    By resultEle = By.id("result");

    public AlertPage (WebDriver driver)
    {
        this.driver = driver;
    }

    private Alert alertObject()
    {
        return driver.switchTo().alert();
    }
    public void alertAccept()
    {
        alertObject().accept();
    }
    public void alertDismiss()
    {
        alertObject().dismiss();
    }
    public String getAlertmsg()
    {
        return alertObject().getText();
    }
    public void sendAlerttext(String Sendtext)
    {
        alertObject().sendKeys(Sendtext);
    }

    public void clickjsAlert()
    {
        driver.findElement(jsAlertEle).click();
    }
    public void clickjsconfirmAlert()
    {
        driver.findElement(jsconfirmAlertEle).click();
    }
    public void clickjspromptAlert()
    {
        driver.findElement(jspromptAlertEle).click();
    }
    public String getresultText()
    {
        return driver.findElement(resultEle).getText();
    }

}
