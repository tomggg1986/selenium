package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    private static WebDriver driver;
    private static final String BOOTSTRAP_PAGE = "http://tomaszgolebiowski.co.nf/bootstrap/index.html";

    private static final String XPATH_1 = "/html/body/div/div[2]/div[2]/form/div[12]/div/input";
    private static final String XPATH_2 = "//input[@id=\"result\"]";
    private static final String XPATH_3 = "//*[@id=\"result\"]";
    //private static final String XPATH_4 = "//*[@class=\"result\"]";

    @BeforeAll
    static void createDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--mute-audio");
        driver = new ChromeDriver(options);
    }

    @Test
    void dataSummaryTest(){
        driver.get(BOOTSTRAP_PAGE);
        driver.findElement(By.id("firstName")).sendKeys("Jan");
        driver.findElement(By.id("lastName")).sendKeys("Kowalski");
        driver.findElement(By.id("username")).sendKeys("JanK");
        driver.findElement(By.id("email")).sendKeys("janK@wp.pl");
        driver.findElement(By.className("btn-primary")).click();

        String nameValue = driver.findElement(By.xpath(XPATH_3)).getAttribute("value");
        assertEquals("Jan" + " " + "Kowalski", nameValue);
    }


    @AfterAll
    static void closeDriver(){
        driver.quit();
    }

}
