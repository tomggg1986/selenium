package SeleniumReader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class SeleniumReader {

    private static final ClassLoader loader = SeleniumReader.class.getClassLoader();
    private String chromeDriverPath;
    private WebDriver driver;

    {
        chromeDriverPath = loader.getResource("chromedriver.exe").getPath().toString();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    public SeleniumReader() {
        driver = new ChromeDriver(new ChromeOptions());
    }

    public void closeDriver() {
        driver.close();
    }

    public void waitUntilPageIsReady() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until((ExpectedCondition<Boolean>) e -> ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
            ).equals("complete"));
        } catch (RuntimeException e) {
            //log.error("Page load not completed");
        }
    }

    /**
     * navigation over pages
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * reads values form page elements
     */
    public String readValueFromInputById(String id) {
        System.out.println("Read Value form input form");
        return driver.findElement(By.id(id)).getAttribute("value");

    }

    public void setValueToInputById(String id, String value) {
        System.out.println("Set Value to input form");
        driver.findElement(By.id(id)).sendKeys(value);
    }

    public String getTextFormElement(String id) {
        return driver.findElement(By.id(id)).getText();
    }

    public Optional<WebElement> waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return  Optional.ofNullable(wait.until(ExpectedConditions.visibilityOf(element)));
    }

    public void setWatiTimeinSeconds(long time){
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
