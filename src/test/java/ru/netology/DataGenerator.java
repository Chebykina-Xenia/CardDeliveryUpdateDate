package ru.netology;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserInfo {
        private String city;
        private String name;
        private String phone;
    }

    private DataGenerator() {
    }

    //генерируем дату, shift — количество дней на которое сдвигаем
    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    //генерируем номер телефона
    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().subscriberNumber(10);
    }

    //генерируем город
    public static String generateCity() {
        String[] city = {"Вологда", "Краснодар", "Архангельск", "Калининград", "Самара", "Владимир"};
        int rnd = new Random().nextInt(city.length);
        return city[rnd];
    }

    //генерируем имя
    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().nameWithMiddle();
        //разделяем строку и получаем массив из фамилии, имени и отчества
        String[] words = name.split(" ");
        //соединяем только имя и отчество
        String nameUser = words[0] + " " + words[1];
        return nameUser;
    }
}
