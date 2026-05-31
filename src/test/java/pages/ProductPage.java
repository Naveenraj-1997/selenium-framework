package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    WebDriver driver;

    By productPageTitleEle = By.className("app_logo");
    By addToCartButtonEle = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
    //ancestor::div[@class='inventory_item_description']//button
    ////button[@class='btn btn_primary btn_small btn_inventory ']
    By productNameEle = By.cssSelector("div[class='inventory_item_name']");
    By productSortEle = By.className("product_sort_container");
    By productPriceEle = By.xpath("//div[@class ='inventory_item_price']");

    public ProductPage(WebDriver driver)
    {

        this.driver = driver;
    }

    public boolean isProductpageTitledisplayed()
    {
        return driver.findElement(productPageTitleEle).isDisplayed();
    }
    //public void clickAddToCart(String productName)
    //{
      //  List<WebElement> productsList = driver.findElements(productNameEle);
        //List<WebElement> buttonsList = driver.findElements(addToCartButtonEle);
        //for(int i =0; i<productsList.size(); i++)
        //{
          //  if(productsList.get(i).getText().equals(productName))
            //{
              //  buttonsList.get(i).click();
                //break;
            //}
        //}
    //}

    public void clickAddToCart(String productName) {
        String formattedName = "add-to-cart-" +
                productName.toLowerCase().replace(" ", "-");

        System.out.println("Looking for: " + formattedName);

       // WebElement button = driver.findElement(By.cssSelector("[data-test='" + formattedName + "']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button =  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='" + formattedName + "']")));
        System.out.println("Button found: " + button.isDisplayed());
        System.out.println("Button enabled: " + button.isEnabled());

        button.click();
        System.out.println("Button clicked!");
    }

    private Select sortDropdown()
    {
        WebElement productEle = driver.findElement(productSortEle);
        return new Select(productEle);
    }
    public void sortProducts(String sortOption)
    {
        sortDropdown().selectByVisibleText(sortOption);
    }

    public String getfirstSelectedOption()
    {
        return sortDropdown().getFirstSelectedOption().getText();
    }
    public String getFirstProductPrice()
    {
        return driver.findElement(productPriceEle).getText();
    }
    public List<String> getAllProductPrice()
    {
        List<WebElement> allProductPrices = driver.findElements(productPriceEle);
        List<String> prices = new ArrayList<>();
        for(WebElement price :allProductPrices)
        {
            prices.add(price.getText());
        }
        return prices;
    }

    
}
