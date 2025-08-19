package ecom.Tests;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.TestComponents.BaseTest;
import ecom.pageobjects.LoginPage;
import ecom.pageobjects.ProductCataloguePage;

public class FiltersTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(FiltersTest.class);

    @Test(groups = {"filters"})
    public void testSearchFilter() {
        logger.info("Running Search Filter test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.searchProduct("ZARA COAT 3");
        List<String> products = catalogue.getVisibleProductNames();
        logger.info("Search results: {}", products);

        Assert.assertTrue(products.contains("ZARA COAT 3"), "ZARA COAT 3 should be visible after search");
    }

    @Test(groups = {"filters"},        
    		retryAnalyzer = ecom.TestComponents.Retry.class
)
    public void testPriceRangeFilter() throws InterruptedException {
        logger.info("Running Price Range Filter test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.setPriceRange("20000", "40000");
        Thread.sleep(2000); 

        List<String> products = catalogue.getVisibleProductNames();
        System.out.println(products);

        logger.info("Price filter results: {}", products);

        Assert.assertTrue(products.containsAll(Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL")),
                "Expected Zara Coat 3 and Adidas Original in price range");
    }

    @Test(groups = {"filters"})
    public void testCategoryFashion() {
        logger.info("Running Category - Fashion test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectFashion();
        List<String> products = catalogue.getVisibleProductNames();
        logger.info("Fashion filter results: {}", products);

        Assert.assertTrue(products.containsAll(Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL")));
    }

    @Test(groups = {"filters"})
    public void testCategoryElectronics() {
        logger.info("Running Category - Electronics test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectElectronics();
        List<String> products = catalogue.getVisibleProductNames();
        logger.info("Electronics filter results: {}", products);

        Assert.assertTrue(products.contains("IPHONE 13 PRO"));
    }

    @Test(groups = {"filters"})
    public void testCategoryHousehold() {
        logger.info("Running Category - Household test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectHousehold();
        Assert.assertTrue(catalogue.isToastVisible(), "Toast should appear since no products exist in Household");
    }

    @Test(groups = {"filters"})
    public void testSubCategoryShirts() {
        logger.info("Running SubCategory - Shirts test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectShirts();
        List<String> products = catalogue.getVisibleProductNames();
        logger.info("Shirts filter results: {}", products);

        Assert.assertTrue(products.containsAll(Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL")));
    }

    @Test(groups = {"filters"})
    public void testSubCategoryTshirts() {
        logger.info("Running SubCategory - T-Shirts test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectTshirts();
        Assert.assertTrue(catalogue.isToastVisible(), "Toast should appear since no products exist in T-Shirts");
    }

    @Test(groups = {"filters"})
    public void testSubCategoryShoes() {
        logger.info("Running SubCategory - Shoes test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);
        
        catalogue.selectShoes();
        Assert.assertTrue(catalogue.isToastVisible(), "Toast should appear (bug: no shoes visible)");
    }

    @Test(groups = {"filters"})
    public void testSubCategoryMobiles() {
        logger.info("Running SubCategory - Mobiles test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectMobiles();
        List<String> products = catalogue.getVisibleProductNames();
        logger.info("Mobiles filter results: {}", products);

        Assert.assertTrue(products.contains("IPHONE 13 PRO"));
    }

    @Test(groups = {"filters"})
    public void testSubCategoryLaptops() {
        logger.info("Running SubCategory - Laptops test");

        LoginPage loginP = new LoginPage(driver);
        loginP.loginApplication("rebofkatwal10@gmail.com", "Pass@123");

        ProductCataloguePage catalogue = new ProductCataloguePage(driver);

        catalogue.selectLaptops();
        Assert.assertTrue(catalogue.isToastVisible(), "Toast should appear since no products exist in Laptops");
    }
}
