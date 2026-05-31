package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage {

    WebDriver driver;
    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.cssSelector(".submit-button.btn_action");
    By errorMessage = By.xpath("//h3[@data-test = 'error']");

    public LoginPage (WebDriver driver)
    {

        this.driver = driver;
    }
    public void enterUsername_Password (String UN, String PWD)
    {
        driver.findElement(username).sendKeys(UN);
        driver.findElement(password).sendKeys(PWD);
    }
    public void clickLoginButton ()
    {
        driver.findElement(loginButton).click();
    }
    public void login(String UN, String PWD)
    {
        enterUsername_Password(UN,PWD);
        clickLoginButton();
    }
    public boolean isErrorMessageDisplayed()
    {
        return driver.findElement(errorMessage).isDisplayed();
    }
    public String getErrorMessage()
    {
        return driver.findElement(errorMessage).getText();
    }






}
