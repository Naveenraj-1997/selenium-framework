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
        products.clickAddToCart(config.getProductName());
    }
    @Test
    public void verifyProductSortdropdown()
    {
        products.sortProducts("Price (high to low)");
        Assert.assertEquals(products.getfirstSelectedOption(), "Price (high to low)");
        System.out.println("The selected option is "+products.getfirstSelectedOption());

        Assert.assertEquals(products.getFirstProductPrice(), "$49.99");
        System.out.println("The first option price is "+products.getFirstProductPrice());
    }

}
