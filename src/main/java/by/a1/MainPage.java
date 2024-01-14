package by.a1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.a1.by/");
    }

    @FindBy(xpath = "//*[@id='CookiesStickyPanel']//button[@data-action-button='rejectAll']")
    WebElement rejectCookie;
    @FindBy(xpath = "//*[@id='CookiesStickyPanel']//button[@data-action-button='acceptAll']")
    WebElement acceptCookie;
    @FindBy(xpath = "//section[@class='footer-bottom']")
    private WebElement footer;
    @FindBy(xpath = "//input[@id ='i-subscribe-input']")
    private WebElement emailField;
    @FindBy(xpath = "//fieldset//label/button")
    public WebElement submitButton;

    public final String message = "//p[@class='iziToast-message slideIn']";
    @FindBy(xpath = "//p[@class='iziToast-message slideIn']")
    public WebElement messageWindow;
    public final String messageText = "//p[@class='iziToast-message slideIn']//div[@class='toast-content-text']";


    //Метод, чтобы принять файлы cookie
    public void acceptCookies() {
        acceptCookie.click();
    }

    //Метод, чтобы отклонить файлы cookie
    public void rejectCookies() {
        rejectCookie.click();
    }

    //Метод прокрутки до поля внизу страницы
    public void scrollToEmail(){
        actions.sendKeys(Keys.END).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("top-offers-phones")));
        actions.scrollToElement(footer).perform();

    }

    //Метод ввода адреса и нажатия на кнопку подтверждения
    public void enterEmail(String email){
        emailField.sendKeys(email);
        actions.moveToElement(submitButton).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(messageText)));

    }
    //Метод объединяет прокрутку до поля ввода адреса, ввод и нажатие кнопки
    public String resultMesageAfterEnterEmail(String email){
        scrollToEmail();
        enterEmail(email);
        return driver.findElement(By.xpath(messageText)).getText();

    }

}
