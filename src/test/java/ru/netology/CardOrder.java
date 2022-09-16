package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrder {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void happyPath() {

        driver.findElement(By.name("name")).sendKeys("Елена Левина-Иванова");
        driver.findElement(By.name("phone")).sendKeys("+79175554433");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String errorText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", errorText.trim());
    }


    // Задание №2 "Проверка валидации"

    @Test
    public void invalidName() {

        driver.findElement(By.name("name")).sendKeys("Elena Levina");
        driver.findElement(By.className("button__text")).click();

        String errorText = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", errorText.trim());
    }

    @Test
    public void invalidNoName() {

        driver.findElement(By.name("name")).sendKeys("");
        driver.findElement(By.className("button__text")).click();

        String errorText = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", errorText.trim());
    }

    @Test
    public void invalidPhone() {

        driver.findElement(By.name("name")).sendKeys("Елена Левина-Иванова");
        driver.findElement(By.name("phone")).sendKeys("+791755544");
        driver.findElement(By.className("button__text")).click();

        String errorText = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", errorText.trim());
    }
    @Test
    public void invalidNoPhone() {

        driver.findElement(By.name("name")).sendKeys("Елена Левина-Иванова");
        driver.findElement(By.name("phone")).sendKeys("");
        driver.findElement(By.className("button__text")).click();

        String errorText = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", errorText.trim());
    }

    @Test
    void invalidNoClikCheckBox() {

        driver.findElement(By.name("name")).sendKeys("Елена Левина-Иванова");
        driver.findElement(By.name("phone")).sendKeys("+79175554433");
        driver.findElement(By.className("button__text")).click();

        String errorText = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", errorText.trim());
    }


}
