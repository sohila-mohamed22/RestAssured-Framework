package com.platziapi.utils.datamanager;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    private final static Faker faker = new Faker();

    public static String getTitle(){
        return faker.name().title();
    }
    public static int getPrice() {
        return faker.number().numberBetween(1, 1000);
    }
    public static String getDescription(){
        return faker.lorem().sentence();
    }
    public static int getId(){
        return faker.number().randomDigit();
    }
    public static ArrayList<String> getImages(){
        return new ArrayList<>(List.of(faker.avatar().image()));
    }
    public static String getEmail(){
        return faker.internet().emailAddress();
    }
    public static String getPassword(){
        return faker.internet().password();
    }

}
