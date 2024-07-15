package com.bestbuy.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class TestUtils {

    public static String getRandomValue() {
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }

    public static String generateName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateType() {
        Faker faker = new Faker();
        return faker.options().option("Mobile", "Laptop", "LED");
    }

    public static int generatePrice() {
        Faker faker = new Faker();
        return faker.number().numberBetween(50, 1000);
    }

    public static double generateShipping() {
        Faker faker = new Faker();
        return faker.number().randomDouble(2, 4, 15);
    }

    public static String generateManufacturer() {
        Faker faker = new Faker();
        return faker.company().name();
    }

    public static String generateUrl() {
        Faker faker = new Faker();
        return faker.internet().url();
    }

    public static String generateImageUrl() {
        Faker faker = new Faker();
        return faker.internet().image();
    }



}
