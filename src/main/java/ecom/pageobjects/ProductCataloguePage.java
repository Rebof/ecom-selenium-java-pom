package ecom.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.common.CommonComponents;

public class ProductCataloguePage extends CommonComponents {

    // Locators
    private By productsLocator = By.cssSelector(".mb-3");
    private By productNameTag = By.cssSelector("b");
    private By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    private By toastContainer = By.cssSelector("#toast-container"); // when no products

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Sidebar elements
    @FindBy(xpath = "//div[@class='py-2 border-bottom ml-3']//input[@placeholder='search']")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@class='col-md-6']//input[@placeholder='Min Price']")
    private WebElement minBox;

    @FindBy(xpath = "//div[@class='py-2 border-bottom ml-3']//input[@placeholder='Max Price']")
    private WebElement maxBox;

    // Categories
    @FindBy(xpath = "//section[@id='sidebar']//div[3]//div[2]//input[1]")
    private WebElement fashionCheck;

    @FindBy(xpath = "//section[@id='sidebar']//div[3]//div[3]//input[1]")
    private WebElement electronicsCheck;

    @FindBy(xpath = "//section[@id='sidebar']//div[3]//div[4]//input[1]")
    private WebElement householdCheck;

    // Sub Categories
    @FindBy(xpath = "//section[@id='sidebar']//div[4]//div[2]//input[1]")
    private WebElement tshirtsCheck;

    @FindBy(xpath = "//section[@id='sidebar']//div[4]//div[3]//input[1]")
    private WebElement shirtsCheck;

    @FindBy(xpath = "//section[@id='sidebar']//div[4]//div[4]//input[1]")
    private WebElement shoesCheck;

    @FindBy(xpath = "//div[@class='py-2 border-bottom ml-3']//div[5]//input[1]")
    private WebElement mobilesCheck;

    @FindBy(xpath = "//section[@id='sidebar']//div[6]//input[1]")
    private WebElement laptopsCheck;

    // ---------- Core Methods 

    public List<WebElement> getProductList() {
        waitForVisibility(productsLocator, 5);
        return driver.findElements(productsLocator);
    }

    public WebElement getProductByName(String productName) {
        return getProductList()
                .stream()
                .filter(product -> product.findElement(productNameTag).getText().equals(productName))
                .findFirst()
                .orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        if (prod != null) {
            prod.findElement(addToCartButton).click();
        } else {
            throw new RuntimeException("Product not found: " + productName);
        }
    }

    // ---------- Search & Price Methods 

    public void searchProduct(String productName) {
        searchBox.clear();
        searchBox.sendKeys(productName);
    }

    public void setPriceRange(String min, String max) {
        minBox.clear();
        minBox.sendKeys(min);
        maxBox.clear();
        maxBox.sendKeys(max);
        maxBox.sendKeys(Keys.ENTER);

        
    }

    // ---------- Category Methods

    public void selectFashion() {
        fashionCheck.click();
    }

    public void selectElectronics() {
        electronicsCheck.click();
    }

    public void selectHousehold() {
        householdCheck.click();
    }

    // ---------- Sub Category Methods 

    public void selectTshirts() {
        tshirtsCheck.click();
    }

    public void selectShirts() {
        shirtsCheck.click();
    }

    public void selectShoes() {
        shoesCheck.click();
    }

    public void selectMobiles() {
        mobilesCheck.click();
    }

    public void selectLaptops() {
        laptopsCheck.click();
    }

    // ---------- Helper Methods

//    public List<String> getVisibleProductNames() {
//        List<WebElement> freshList = getProductList();
//
//        return freshList.stream()
//                .map(p -> p.findElement(productNameTag).getText())
//                .collect(Collectors.toList());
//    }
    


    
    public List<String> getVisibleProductNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> products = null;

        // Retry in case of stale exc
        int attempts = 0;
        while (attempts < 3) {
            try {
                products = getProductList(); // fetch fresh list 
                for (WebElement product : products) {
                    names.add(product.findElement(productNameTag).getText());
                }
                return names; 
            } catch (StaleElementReferenceException e) {
                names.clear(); // retry
                attempts++;
            }
        }

        throw new RuntimeException("Unable to fetch product names after retries");
    }


    public boolean isToastVisible() {
        return driver.findElements(toastContainer).size() > 0;
    }
}
