package ecom.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        
        String browserName = prop.getProperty("browser");
        //boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
        
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
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