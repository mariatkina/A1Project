package by.a1;
/*  к вечеру понедельника - написать автотест,
суть которого в следующем: должен открыться сайт a1.by,
пролистать до поля ввода адреса электронной почты для подписки на рассылку
и пройти проверку на соответствие текста в окошке,
которое всплывает после ввода адреса электронной почты и нажатия кнопки подтверждения.
Особые условия: при написании автотеста
используйте maven, java 17, Selenium, JUnit 5, webdriver manager, паттерн page object, паттерн singletone.*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class A1Test {
    //+логирование:
    //private static Logger logger = LoggerFactory.getLogger(A1Test.class);
    WebDriver driver;
    MainPage mainPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @ParameterizedTest
    @CsvSource(value = {
            "'user03102023@user.iu', 'Вы успешно подписались на нашу новостную рассылку.'",
            "'xxxxx', 'Проверьте указанный email.'",
            "'@user.iu', 'Проверьте указанный email.'",
            "'user03102023@useriu', 'Проверьте указанный email.'",
            "'       ', 'Проверьте указанный email.'",
            "'', 'Проверьте указанный email.'"
    })
    @DisplayName("тест проверки на соответствие текста во всплывающем окне, после ввода адреса электронной почты и нажатия кнопки подтверждения")
    void Test1(String mailAdress, String expectedResult) {
        mainPage.rejectCookies();
        //или:
        //mainPage.acceptCookies();
        mainPage.scrollToEmail();

        Assertions.assertEquals(expectedResult, mainPage.resultMesageAfterEnterEmail(mailAdress));

    }

    @AfterEach
    void closeBrowser() {
        driver.close();
    }
}
