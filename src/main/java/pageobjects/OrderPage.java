package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //  Локатор формы для заказа
    private static final By orderForm = By.className("Order_Form__17u6u");

    // Локаторы полей для ввода данных
    private static final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private static final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By subwayList = By.className("select-search__value");
    private static final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Локатор кнопки Далее
    private static final By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Дождаться, пока страница загрузится
    public void waitForOrderPageToLoad() {
        wait.until(visibilityOfElementLocated(orderForm));
    }

    //Ввести имя
    public void setName(String orderName) {
        driver.findElement(nameField).sendKeys(orderName);
    }

    //Ввести фамилию
    public void setSurname(String orderSurname) {
        driver.findElement(surnameField).sendKeys(orderSurname);
    }

    //Ввести адрес
    public void setAddress(String orderAddress) {
        driver.findElement(addressField).sendKeys(orderAddress);
    }

    //Ввести метро
    public void setSubway(String subwayName) {
        driver.findElement(subwayField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(subwayList));
        driver.findElement(By.xpath(".//*[text()='" + subwayName + "']")).click();
    }

    //Ввести телефон
    public void setPhone(String orderPhone) {
        driver.findElement(phoneField).sendKeys(orderPhone);
    }

    // Нажать "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Ввести данные шагом и перейти дальше
    public void setCredentials(String orderName, String orderSurname, String orderAddress, String subwayName, String orderPhone) {
        setName(orderName);
        setSurname(orderSurname);
        setAddress(orderAddress);
        setSubway(subwayName);
        setPhone(orderPhone);
        clickNextButton();
    }
}
