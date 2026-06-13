package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActionsPage {
    WebDriver driver;
    Actions actions;

    By imageEle = By.cssSelector(".figure");
    By captionEle = By.cssSelector(".figcaption h5");
    By sourceEle = By.id("column-a");
    By targetEle = By.id("column-b");

    public MouseActionsPage(WebDriver driver){
        this.driver = driver;
        this.actions = new Actions(driver);
    }
    public void mouseHoverElement(WebElement element)
    {
        actions.moveToElement(element).perform();
    }
    public WebElement getUserimage(int index)
    {
        return driver.findElements(imageEle).get(index);
    }

    public String getCaptionText(int index)
    {
        return driver.findElements(captionEle).get(index).getText();
    }
    public void DragAndDrop()
    {
        WebElement source = driver.findElement(sourceEle);
        WebElement target = driver.findElement(targetEle);
        actions.dragAndDrop(source,target).perform();
    }
    public String getColumnAText() {
        return driver.findElement(sourceEle).getText();
    }


}
