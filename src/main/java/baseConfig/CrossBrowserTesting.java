package baseConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class CrossBrowserTesting {

    public static WebDriver driver;
    protected static WebDriverWait wait;
    protected Actions action;
    protected static JavascriptExecutor js;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("firefox") String browser) {

        driver = initializeDriver(browser);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;

        System.out.println(browser + " browser is launched successfully.");
    }

    private WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new ChromeOptions());
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(new EdgeOptions());
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(new FirefoxOptions());
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser + ". Please specify 'chrome', 'edge', or 'firefox'.");
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
