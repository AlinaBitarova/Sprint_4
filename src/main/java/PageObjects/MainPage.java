package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Вопросы о важном (список)
    private By faqList = By.className ("accordion");

    // Локатор для вопросов
    private By faqListQuestions  = By.className("accordion__button");

    // Локатор для ответов
    private By faqListAnswers  = By.className("accordion__panel");

    // Локатор кнопки заказать (верхняя)
    private By orderButtonTop = By.className("Button_Button__ra12g");

    // Локатор кнопки заказать (нижняя)
    private By orderButtonBottom = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Локатор для куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public MainPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Принять куки
    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    // Прокрутить до списка вопросов
    public void scrollToFaqList() {
        wait.until(visibilityOfElementLocated(faqList));
        WebElement faqListBlock = driver.findElement(faqList);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", faqListBlock);
    }

    // Получить текст вопросов
    public String getQuestionText(int i) {
        List<WebElement> questions = driver.findElements(faqListQuestions);
        return questions.get(i).getText();
    }

    // Нажать на вопрос
    public void clickQuestion(int i) {
        List<WebElement> questions = driver.findElements(faqListQuestions);
        WebElement question = questions.get(i);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
    }

    // Получить текст ответа
    public String getAnswerText (int i) {
        List<WebElement> answers = driver.findElements(faqListAnswers);
        WebElement answer = answers.get(i);
        wait.until(ExpectedConditions.visibilityOf(answer));
        return answer.getText();
    }

    // Нажать на верхнюю кнопку заказать
    public void clickOrderTop() {
        driver.findElement(orderButtonTop).click();
    }

    // Нажать на нижнюю кнопку заказать
    public void clickOrderBottom() {
        WebElement orderBottomButton = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", orderBottomButton);
        driver.findElement(orderButtonBottom).click();
    }

}
