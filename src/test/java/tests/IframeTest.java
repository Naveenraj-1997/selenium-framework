package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IframeTest extends BaseTest{

@BeforeMethod
    public void IframeSetup()
{
    driver.get(config.getIframeURL());
}

@Test
    public void verifyframeText()
{
    iframePage.switchtoIframe();
    Assert.assertEquals(iframePage.getFrameText(), "Your content goes here.");
    iframePage.swithToMainpage();
}

@Test
    public void typeframeText()
{
    iframePage.switchtoIframe();
    iframePage.typeText("Naveen");
    Assert.assertEquals(iframePage.getFrameText(),"Naveen");
    iframePage.swithToMainpage();


}
}
