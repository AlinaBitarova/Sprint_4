package PageObjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private WebDriver driver;
    private MainPage objMainPage;
    private OrderPage objOrderPage;
    private AboutRentPage objAboutRentPage;
    private final String orderName;
    private final String orderSurname;
    private final String orderAddress;
    private final String subwayName;
    private final String orderPhone;
    private final String orderDate;
    private final String rentDuration;
    private final String color;
    private final String noteForCourier;

    public CreateOrderTest(String orderName, String orderSurname, String orderAddress, String subwayName, String orderPhone, String orderDate, String rentDuration, String color, String noteForCourier) {
        this.orderName = orderName;
        this.orderSurname = orderSurname;
        this.orderAddress = orderAddress;
        this.subwayName = subwayName;
        this.orderPhone = orderPhone;
        this.orderDate = orderDate;
        this.rentDuration = rentDuration;
        this.color = color;
        this.noteForCourier = noteForCourier;
}

    @Parameterized.Parameters
    public static Object[][] getInfo() {
        return new Object[][]{
                {"Юри", "Кацуки", "Лунная 17", "Чистые пруды", "+79998887766", "Choose четверг, 20-е августа 2026 г.", "сутки", "черный жемчуг", "Возле зимнего дворца спорта"},
                {"Виктор", "Никифоров", "Тренировочная, 9", "Ясенево", "+76665554433", "Choose пятница, 14-е августа 2026 г.", "семеро суток", "серая безысходность", "Не звоните"}
        };
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        objMainPage = new MainPage(driver);
        objMainPage.acceptCookies();
        objOrderPage = new OrderPage(driver);
        objAboutRentPage = new AboutRentPage(driver);
    }

    // Тест верхней кнопки
    @Test
    public void checkTopOrderButton() {
        objMainPage.clickOrderTop();
        objOrderPage.waitForOrderPageToLoad();
        objOrderPage.setCredentials(orderName, orderSurname, orderAddress, subwayName, orderPhone);
        objAboutRentPage.waitForRentPageToLoad();
        objAboutRentPage.setRentInfo(orderDate, rentDuration, color, noteForCourier);
        assertTrue(objAboutRentPage.checkOrderIsSuccessful());
    }

    // Тест нижней кнопки
    @Test
    public void checkBottomOrderButton() {
        objMainPage.clickOrderBottom();
        objOrderPage.waitForOrderPageToLoad();
        objOrderPage.setCredentials(orderName, orderSurname, orderAddress, subwayName, orderPhone);
        objAboutRentPage.waitForRentPageToLoad();
        objAboutRentPage.setRentInfo(orderDate, rentDuration, color, noteForCourier);
        assertTrue(objAboutRentPage.checkOrderIsSuccessful());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}