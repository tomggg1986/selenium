package tests;

import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumTest {

    public static final String NAME = "Jan";
    public static final String LAST_NAME = "Kowalski";
    public static final String USER_NAME = "JanK";
    public static final String EMAIL = "jank@wp.pl";
    public static final String ADDRESS = "Dzika 12";
    public static final String PAYMENT_METHOD = "debit";
    public static final String BOOTSTRAP_PAGE = "http://tomaszgolebiowski.co.nf/bootstrap/index.html";

    @BeforeAll
    public void startBrowser() {
//        EnvironmentManager.initChromeWebDriver();
        EnvironmentManager.initFileFoxWebDriver();
//        EnvironmentManager.initEdgeWebDriver();
//        EnvironmentManager.initInternetExplorerWebDriver();
    }

    @Test
    public void testIfPageLoadSuccessful() {
        WebDriver driver = RunEnvironment.getWebDriver();
        driver.get(BOOTSTRAP_PAGE);
        String homeUrl = driver.getCurrentUrl();
        assertEquals(homeUrl, BOOTSTRAP_PAGE);
    }

    @Test
    public void fillFormInputs() throws InterruptedException {
        WebDriver driver = RunEnvironment.getWebDriver();
        driver.findElement(By.id("firstName")).sendKeys(NAME);
        driver.findElement(By.id("lastName")).sendKeys(LAST_NAME);
        driver.findElement(By.id("username")).sendKeys(USER_NAME);
        driver.findElement(By.id("email")).sendKeys(EMAIL);
        driver.findElement(By.id("address")).sendKeys(ADDRESS);

        Select drpCountry = new Select(driver.findElement(By.id("country")));
        drpCountry.selectByVisibleText("United States");

        Select drpState = new Select(driver.findElement(By.id("state")));
        drpState.selectByVisibleText("California");

        driver.findElement(By.id("zip")).sendKeys("100-10");

        // select payment method form radio button in firefox
        WebElement payment = driver.findElement(By.id(PAYMENT_METHOD));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payment);

        // select payment method form radio button
        Actions act1 = new Actions(driver);
        act1.moveToElement(driver.findElement(By.id(PAYMENT_METHOD)))
                .click()
                .build()
                .perform();

        driver.findElement(By.id("cc-name")).sendKeys("gold visa");
        driver.findElement(By.id("cc-number")).sendKeys("1234");
        driver.findElement(By.id("cc-expiration")).sendKeys("3333");
        driver.findElement(By.id("cc-cvv")).sendKeys("5555");

        //click Button named: Continue to checkout
        driver.findElement(By.className("btn-primary")).click();

    }

    @Test
    public void readDataSummaryValue() {
        WebDriver driver = RunEnvironment.getWebDriver();
        String nameValue = driver.findElement(By.id("result")).getAttribute("value");
        assertEquals(nameValue, NAME + " " + LAST_NAME);
    }

    @Test
    public void readSelectedPaymentMethod() {
        WebDriver driver = RunEnvironment.getWebDriver();
        String nameValue = driver.findElement(By.id("selectedPaymentMethod")).getAttribute("value");
        assertEquals(nameValue, PAYMENT_METHOD);
    }

    @AfterAll
    public void tearDown() {
        // EnvironmentManager.shutDownDriver();
    }
}