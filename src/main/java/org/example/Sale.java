package org.example;

import java.time.LocalDate;

public record Sale(Merchant merchant, LocalDate date, double value) {
}
