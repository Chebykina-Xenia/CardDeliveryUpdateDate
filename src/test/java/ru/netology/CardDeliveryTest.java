package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    RegistrationByUserInfo userInfo = DataGenerator.Registration.generateByUser("ru");

    @BeforeEach
    public void setUp() {
        //открываем сайт
        open("http://localhost:9999/");
    }

    @Test
    void shouldPreventSendRequestMultipleTimes() {
        System.out.println(userInfo.getCity());
        System.out.println(userInfo.getDate());
        System.out.println(userInfo.getName());
        System.out.println(userInfo.getPhone());
    }

    //заполняем все поля — успешное отправление формы
    @Test
    void shouldFormSentSuccessfully() {

        String userPhone = userInfo.getPhone();

        //заполняем поле город
        $("[class='input__control'][placeholder='Город']").setValue("Краснодар");
        $("[class='menu-item__control']").click();
        //заполняем поле дата
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] input").click();
        $("[class='input__control'][placeholder='Дата встречи']").setValue(userInfo.getDate());
        //заполняем поле имя и фамилия
        $("[class='input__control'][name='name']").setValue("Чебыкина Ксения");
        //заполняем поле телефон
        $("[class='input__control'][name='phone']").setValue("+" + userPhone);
        //ставим галочку согласия
        $("[data-test-id='agreement']").click();
        //нажимаем кнопку забронировать
        $(byText("Запланировать")).click();

        //проверка успешной отправки формы
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
        //заполняем поле дата
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id='date'] input").click();
        $("[class='input__control'][placeholder='Дата встречи']").setValue(userInfo.getDate());
        //нажимаем кнопку забронировать
        $(byText("Запланировать")).click();
        //проверка успешной отправки формы
        $("[data-test-id='replan-notification'] [class='notification__title']").shouldHave(exactText("Необходимо подтверждение"));
        $(byText("Перепланировать")).click();
        $("[class='notification__title']").shouldHave(exactText("Успешно!"));
    }
}
