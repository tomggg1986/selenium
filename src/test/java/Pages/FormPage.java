package Pages;

import environment.RunEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FormPage {

    private static final String ADDRESS = "Dzika 12";
    private static final String BOOTSTRAP_PAGE = "http://tomaszgolebiowski.co.nf/bootstrap/index.html";

    private WebDriver driver;

    public FormPage() {
        driver = RunEnvironment.getWebDriver();
    }

    public FormPage setPersonalData(String name, String lastName, String userNamem, String email) {
        driver.get(BOOTSTRAP_PAGE);
        driver.findElement(By.id("firstName")).sendKeys(name);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("username")).sendKeys(userNamem);
        driver.findElement(By.id("email")).sendKeys(email);
        return this;
    }


    public FormPage setAddress() {

        driver.findElement(By.id("address")).sendKeys(ADDRESS);

        Select drpCountry = new Select(driver.findElement(By.id("country")));
        drpCountry.selectByVisibleText("United States");

        Select drpState = new Select(driver.findElement(By.id("state")));
        drpState.selectByVisibleText("California");

        driver.findElement(By.id("zip")).sendKeys("100-10");
        return this;
    }

    public FormPage setPayment(String paymentMethod) {
        // select payment method form radio button in firefox
        WebElement payment = driver.findElement(By.id(paymentMethod));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payment);
        Actions act1 = new Actions(driver);
        act1.moveToElement(driver.findElement(By.id(paymentMethod)))
                .click()
                .build()
                .perform();

        driver.findElement(By.id("cc-name")).sendKeys("gold visa");
        driver.findElement(By.id("cc-number")).sendKeys("1234");
        driver.findElement(By.id("cc-expiration")).sendKeys("3333");
        driver.findElement(By.id("cc-cvv")).sendKeys("5555");
        return this;
    }

    public FormPage clickContinueButton() {
        driver.findElement(By.className("btn-primary")).click();
        return this;
    }

    public String getDataSummary() {
        return driver.findElement(By.xpath("//*[@id=\"result\"]")).getAttribute("value");
    }

    public String getPaymentMethod() {
        return driver.findElement(By.cssSelector("#selectedPaymentMethod")).getAttribute("value");
    }

}

