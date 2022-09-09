package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CardOrder {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriwer.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearsDown(){
        driver.quit();
        driver = null;
    }


}
