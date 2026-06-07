package pages;

import org.openqa.selenium.*;

public class IframePage {
    WebDriver driver;
    By iframeelem = By.id("mce_0_ifr");
    By textbodyEle =   By.id("tinymce");
    By buttonblodEle = By.xpath("//button[@aria-label='Bold']");

    public IframePage(WebDriver driver)
    {
        this.driver = driver;
    }
    public void switchtoIframe()
    {
        WebElement iframe = driver.findElement(iframeelem);
        driver.switchTo().frame(iframe);
    }
    public void swithToMainpage()
    {
        driver.switchTo().defaultContent();
    }
    public String getFrameText()
    {
        return driver.findElement(textbodyEle).getText();
    }

    public void typeText(String text) {
       // WebElement body = driver.findElement(textbodyEle);
        //body.sendKeys(Keys.CONTROL + "a");
        //body.sendKeys(text);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("tinymce.activeEditor.setContent('" + text + "')");
    }
}
