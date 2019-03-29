package tests;

import Pages.CommandPage;
import environment.EnvironmentManager;
import org.junit.jupiter.api.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommandPageTest {


    private CommandPage commandPage;

    @BeforeAll
    void startBrowser() {
        EnvironmentManager.initChromeWebDriver();
//        EnvironmentManager.initFileFoxWebDriver();
//        EnvironmentManager.initEdgeWebDriver();
//        EnvironmentManager.initInternetExplorerWebDriver();
    }

    @BeforeEach
    void createPage() {
        commandPage = new CommandPage();
    }

    @AfterAll
    void shutDown() {
        EnvironmentManager.shutDownDriver();
    }


    @Test
    void addNewCommandTest()  {
        commandPage.openPage()
                .loginToAdminPanel("selenium@kurs.pl", "selenium")
                .insertNewCommend("show dhcp logs", "for dhcp logs", "cisco", "DHCP")
                .navigateToDevicePage("cisco");

        List<String> comm = commandPage.getCommands();

    }

}