package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AboutRentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //  Локатор формы об аренде
    private static final By rentForm = By.className("Order_Form__17u6u");

    // Локаторы полей для ввода данных
    private static final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By calendarObject = By.className("react-datepicker");
    private static final By rentDurationField = By.className("Dropdown-placeholder");
    private static final By rentDurationList = By.className("Dropdown-menu");
    private static final By blackScooterCheckbox = By.xpath(".//input[@id='black']");
    private static final By greyScooterCheckbox = By.xpath(".//input[@id='grey']");
    private static final By noteForCourierField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Локатор кнопки Заказать
    private static final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Окно подтверждения заказ
    private static final By confirmationWindow = By.className("Order_Modal__YZ-d3");

    // Локатор кнопки подтверждения заказа
    private static final By orderConfirmationButton = By.xpath(".//button[text()='Да']");

    // Локатор окна после успешного создания заказа
    private static final By successfulOrderWindow = By.className("Order_Modal__YZ-d3");

    public AboutRentPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Дождаться, пока страница загрузится
    public void waitForRentPageToLoad() {
        wait.until(visibilityOfElementLocated(rentForm));
    }

    // Выбрать дату заказа
    public void setOrderDate(String orderDate){
        driver.findElement(dateField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(calendarObject));
        driver.findElement(By.xpath("//div[@aria-label='" + orderDate + "']")).click();
    }

    // Выбрать срок аренды
    public void setRentDuration(String rentDuration){
        driver.findElement(rentDurationField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentDurationList));
        driver.findElement(By.xpath(".//div[text()='" + rentDuration + "']")).click();
    }

    // Выбрать цвет самоката
    public void chooseScooterColor(String color) {
        if (color.equals("чёрный жемчуг")){
            driver.findElement(blackScooterCheckbox).click();
        } else if (color.equals("серая безысходность")) {
            driver.findElement(greyScooterCheckbox).click();
    }
    }

    // Комментарий для курьера
    public void setNoteForCourier(String noteForCourier) {
        driver.findElement(noteForCourierField).sendKeys(noteForCourier);
    }

    // Нажать "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Подтвердить заказ
    public void confirmOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationWindow));
        driver.findElement(orderConfirmationButton).click();
    }

    // Сделать заказ шагом
    public void setRentInfo(String orderDate, String rentDuration, String color, String noteForCourier) {
        setOrderDate(orderDate);
        setRentDuration(rentDuration);
        chooseScooterColor(color);
        setNoteForCourier(noteForCourier);
        clickOrderButton();
        confirmOrder();
    }

    // Проверить, создан ли заказ
    public boolean checkOrderIsSuccessful() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successfulOrderWindow));
        return driver.findElement(successfulOrderWindow).getText().contains("Заказ оформлен");
    }
}
