package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardOrder {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void happyPath() {

        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Елена Левина");
        driver.findElement(By.name("phone")).sendKeys("+79175554433");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
    }

    // Задание №2 "Проверка валидации"

    /*@Test
    public void invalidName() {

        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Elena Levina");
        driver.findElement(By.name("phone")).sendKeys("+79175554433");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();

        List<WebElement> elements = driver.findElements(By.className("input__sub"));
        String errorText = elements.get(0).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", errorText);
    }

    @Test
    public void invalidPhone() {

        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Елена Левина");
        driver.findElement(By.name("phone")).sendKeys("+791755544");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();

        List<WebElement> elements = driver.findElements(By.className("input__sub"));
        String errorText = elements.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", errorText);
    }*/


}
