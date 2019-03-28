package tests;

import Pages.FormPage;
import environment.EnvironmentManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumTest {

    private static final String NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";
    private static final String PAYMENT_METHOD = "debit";
    private static final String USER_NAME = "Jan";
    private static final String EMAIL = "jank@wp.pl";

    private FormPage formPage;

    private static Stream<Arguments> personalData() {
        return Stream.of(
                Arguments.of("Jan", "Kowalski", "JanK", "janK@wp.pl"),
                Arguments.of("Anna", "Kowalska", "AnnaK", "annaK@wp.pl"),
                Arguments.of("';drop databases", "Kowalski", "JanK", "janK@wp.pl")
        );
    }

    @BeforeAll
    void startBrowser() {
        EnvironmentManager.initChromeWebDriver();
//        EnvironmentManager.initFileFoxWebDriver();
//        EnvironmentManager.initEdgeWebDriver();
//        EnvironmentManager.initInternetExplorerWebDriver();
    }

    @BeforeEach
    void createPage() {
        formPage = new FormPage();
    }

    @AfterAll
    void shutDown() {
        EnvironmentManager.shutDownDriver();
    }

    @ParameterizedTest
    @MethodSource("personalData")
    void dataSummaryValueTest(String name, String lastName, String userName, String email) {
        formPage.setPersonalData(name, lastName, userName, email)
                .setAddress()
                .setPayment(PAYMENT_METHOD)
                .clickContinueButton();

        String nameValue = formPage.getDataSummary();
        assertEquals(name + " " + lastName, nameValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"debit", "credit", "paypal"})
    void selectedPaymentMethodTest(String paymentMethod) {
        formPage.setPersonalData(NAME, LAST_NAME, USER_NAME, EMAIL)
                .setAddress()
                .setPayment(paymentMethod)
                .clickContinueButton();

        String paymentValue = formPage.getPaymentMethod();
        assertEquals(paymentMethod, paymentValue);
    }

}