package org.example;

public record Person(int age) {

    private static final int ADULT_AGE = 18;

    public boolean isAdult() {
        return age >= ADULT_AGE;
    }
}
