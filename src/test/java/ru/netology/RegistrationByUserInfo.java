package ru.netology;

public class RegistrationByUserInfo {
    private final String city;
    private final String date;
    private final String name;
    private final String phone;


    public RegistrationByUserInfo(String city, String date, String name, String phone) {
        this.city = city;
        this.date = date;
        this.name = name;
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
