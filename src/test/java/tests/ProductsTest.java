package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;


public class ProductsTest extends BaseTest{

   @BeforeMethod
   public void productSetup() throws InterruptedException {
       loginPage.login(config.getUsername(), config.getPassword());
   }
    @Test
    public void verifyProductPageTitle()
    {
        Assert.assertTrue(products.isProductpageTitledisplayed());
    }
    @Test
    public void verifyclickAddtocart() throws InterruptedException {
        products.clickAddToCart("Sauce Labs Bike Light");
    }

}
