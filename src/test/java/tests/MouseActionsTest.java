package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MouseActionsTest extends BaseTest{

    @BeforeMethod
    public void mouseactionSetup()
    {
        driver.get(config.getMouseActionURL());
    }

    @Test
    public void verifyHover()
    {
        mouseactionspage.mouseHoverElement(mouseactionspage.getUserimage(0));
        String caption = mouseactionspage.getCaptionText(0);
        System.out.println("caption "+ caption);
        Assert.assertTrue(caption.contains("name"));
    }
    @Test
    public void verifyDragAndDrop() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        System.out.println("Before drag: " + mouseactionspage.getColumnAText());
        mouseactionspage.DragAndDrop();
        Thread.sleep(2000);
        System.out.println("After drag: " + mouseactionspage.getColumnAText());


    }
}
