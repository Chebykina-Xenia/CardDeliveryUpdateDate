package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    //подключаем ALLURE
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        //открываем сайт
        open("http://localhost:9999/");
    }

    //заполняем все поля — успешное отправление формы
    @Test
    void shouldFormSentSuccessfully() {
        //заполняем поле город
        $("[class='input__control'][placeholder='Город']").setValue(DataGenerator.generateCity());
        $("[class='menu-item__control']").click();
        //заполняем поле дата
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] input").click();
        $("[class='input__control'][placeholder='Дата встречи']").setValue(DataGenerator.generateDate(5));
        //заполняем поле имя и фамилия
        $("[class='input__control'][name='name']").setValue(DataGenerator.generateName("ru"));
        //заполняем поле телефон
        $("[class='input__control'][name='phone']").setValue("+7" + DataGenerator.generatePhone("ru"));
        //ставим галочку согласия
        $("[data-test-id='agreement']").click();
        //нажимаем кнопку забронировать
        $(byText("Запланировать")).click();

        //проверка успешной отправки формы
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        //заполняем поле дата
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] input").click();
        $("[class='input__control'][placeholder='Дата встречи']").setValue(DataGenerator.generateDate(10));
        //нажимаем кнопку забронировать
        $(byText("Запланировать")).click();
        //проверка успешной отправки формы
        $("[data-test-id='replan-notification'] [class='notification__title']").shouldHave(exactText("Необходимо подтверждение"));
        $(byText("Перепланировать")).click();
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
    }
}
