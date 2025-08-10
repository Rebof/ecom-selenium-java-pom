package ecom.common;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonComponents {

    protected WebDriver driver; // so child classes can use it, could use public to make it easier as well

    // Constructor to initialize driver
    public CommonComponents(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for an element to be visible on the page.
     * 
     * @param locator The By locator of the element
     * @param timeoutInSeconds Timeout in seconds
     * @return WebElement once visible
     */
    public WebElement waitForVisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    
    public void waitForInvisibility(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for toast message, wait for animation to disappear, then click cart link.
     */
    public void goToCartFromAnywhere() {
        By toastMessage = By.cssSelector("#toast-container");
        By animationOverlay = By.cssSelector(".ng-animating");
        By cartLink = By.cssSelector("[routerlink*='cart']");

        waitForVisibility(toastMessage, 5);
        waitForInvisibility(animationOverlay, 5);
        driver.findElement(cartLink).click();
    }
    
}
