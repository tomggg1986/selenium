package Pages;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandPage {

    public static final By LOGIN_BUTTON = By.xpath("//*[@id=\"log\"]/a");
    public static final By EMAIL = By.id("email");
    public static final By COMMAND_INPUT = By.xpath("//*[@id=\"commendForm\"]");
    public static final By COMMAND_DESCRIPTION = By.xpath("//*[@id=\"descriptionForm\"]");
    public static final By DEVICE_SELECT = By.id("systemForm");
    public static final By CATEGORY_SELECT = By.id("categoryForm");
    public static final By NEW_COMMAND_SAVE_BUTTON = By.id("newCommendBtn");
    public static final By ADD_NEW_COMMAND_PANEL = By.xpath("//*[@id=\"addCommend\"]");
    public static final By COMMANDS = By.xpath("//*[contains(@class,'col-md-4 alignL')]");
    private static final String COMMAND_PAGE = "http://tomaszgolebiowski.co.nf/commands/index.php";
    private static final Map<String, By> navigationMap;

    static {
        navigationMap = ImmutableMap.<String, By>builder()
                .put("cisco", By.xpath("//*[@id=\"navbarNav\"]/ul/li[2]/a"))
                .put("mikrotik", By.xpath("//*[@id=\"navbarNav\"]/ul/li[3]/a"))
                .build();
    }

    private WebDriver driver;

    public CommandPage() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    public CommandPage openPage() {
        driver.get(COMMAND_PAGE);
        waitUntilPageIsReady();
        return this;
    }

    public CommandPage loginToAdminPanel(String login, String password) {
        WebElement loginWindow = driver.findElement(LOGIN_BUTTON);
        loginWindow.click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(EMAIL));

        WebElement loginInput = driver.findElement(EMAIL);
        loginInput.clear();
        loginInput.sendKeys(login);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        driver.findElement(By.id("authUser")).click();

        return this;
    }

    public CommandPage insertNewCommend(String command, String description, String device, String category) {

        WebDriverWait waitForAddCommand = new WebDriverWait(driver, 20);
        waitForAddCommand.until(ExpectedConditions.elementToBeClickable(ADD_NEW_COMMAND_PANEL));
        driver.findElement(ADD_NEW_COMMAND_PANEL).click();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(COMMAND_INPUT));
        driver.findElement(COMMAND_INPUT).sendKeys(command);

        WebDriverWait waitForDescription = new WebDriverWait(driver, 20);
        waitForDescription.until(ExpectedConditions.elementToBeClickable(COMMAND_DESCRIPTION));
        driver.findElement(COMMAND_DESCRIPTION).sendKeys(description);

        Select deviceSelect = new Select(driver.findElement(DEVICE_SELECT));
        deviceSelect.selectByVisibleText(device);

        Select categorySelect = new Select(driver.findElement(CATEGORY_SELECT));
        categorySelect.selectByVisibleText(category);

        driver.findElement(NEW_COMMAND_SAVE_BUTTON).click();

        return this;
    }

    public CommandPage navigateToDevicePage(String device) {
        By deviceBy = navigationMap.get(device);

        driver.findElement(deviceBy).click();
        WebDriverWait waitForDescription = new WebDriverWait(driver, 20);
        waitForDescription.until(ExpectedConditions.elementToBeClickable(By.id("boardch")));

        return this;
    }

    public List<String> getCommands() {
        waitUntilPageIsReady();
        return driver.findElements(COMMANDS).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private void waitUntilPageIsReady() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until((ExpectedCondition<Boolean>) e -> ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState"
            ).equals("complete"));
        } catch (RuntimeException e) {
            //log.error("Page load not completed");
        }
    }

}
