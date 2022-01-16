package ru.netology;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {

    }

    public static class Registration {
        private Registration() {

        }

        public static RegistrationByUserInfo generateByUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationByUserInfo(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.name().name(),
                    faker.phoneNumber().subscriberNumber(11)
            );
        }
    }
}
