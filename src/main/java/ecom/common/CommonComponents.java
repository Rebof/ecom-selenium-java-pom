package ecom.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonComponents {

    protected WebDriver driver; // so child classes can use it

    // Constructor to initialize driver
    public CommonComponents(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for an element to be visible on the page.
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
    
    
    

    /**
     * Takes a screenshot and saves it in ./screenshots with timestamped name.
     * @param fileNamePrefix Prefix for the screenshot file name
     * @return The absolute file path of the saved screenshot
     */
    public String takeScreenshot(String fileNamePrefix) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destDir = System.getProperty("user.dir") + File.separator + "screenshots";
        File destFolder = new File(destDir);
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        File destFile = new File(destFolder, fileNamePrefix + "_" + timestamp + ".png");
        try {
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile.getAbsolutePath();
    }
}
