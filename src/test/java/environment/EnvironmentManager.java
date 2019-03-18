package environment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class EnvironmentManager {

    public static void initChromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\golebiowskit\\IdeaProjects\\academy_selenium\\src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--mute-audio");
        WebDriver driver = new ChromeDriver(options);

        RunEnvironment.setWebDriver(driver);
    }

    public static void initFileFoxWebDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\golebiowskit\\IdeaProjects\\academy_selenium\\src\\main\\resources\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);

        RunEnvironment.setWebDriver(driver);
    }

    public static void initEdgeWebDriver() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\golebiowskit\\IdeaProjects\\academy_selenium\\src\\main\\resources\\MicrosoftWebDriver.exe");
        WebDriver driver = new EdgeDriver();

        RunEnvironment.setWebDriver(driver);
    }

    public static void initInternetExplorerWebDriver() {
        System.setProperty("webdriver.ie.driver", "C:\\Users\\golebiowskit\\IdeaProjects\\academy_selenium\\src\\main\\resources\\IEDriverServer.exe");
        InternetExplorerOptions options =  new InternetExplorerOptions();
        options.ignoreZoomSettings();
        options.withInitialBrowserUrl("");
        WebDriver driver = new InternetExplorerDriver(options);
        driver.manage().window().maximize();
        RunEnvironment.setWebDriver(driver);
    }

    public static void shutDownDriver() {
        RunEnvironment.getWebDriver().quit();
    }


}
