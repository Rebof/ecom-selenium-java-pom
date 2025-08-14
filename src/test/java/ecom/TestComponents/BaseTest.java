package ecom.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    public WebDriver driver;
    public Properties prop;
    
    public WebDriver initializeDriver() throws IOException {
        // Load properties file
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
            "/src/main/java/ecom/resources/GlobalData.properties");
        prop.load(fis);		
        
        
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        
        //boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
        
        if (browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            
            if (browserName.contains("headless")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // modern headless mode
                options.addArguments("--window-size=1920,1080"); // set window size
                driver = new ChromeDriver(options);
            } else {
                driver = new ChromeDriver();
            }
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } 
        else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
        
        return driver;
    }
    
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        // Get current date and time
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        // Build file path with timestamp
        String filePath = System.getProperty("user.dir") + "//reports//" + testCaseName + "_" + timestamp + ".png";
        File destination = new File(filePath);
        
        // Copy file
        FileUtils.copyFile(source, destination);
        
        return filePath;
    }

    
    @BeforeMethod
    public void launchApplication() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}