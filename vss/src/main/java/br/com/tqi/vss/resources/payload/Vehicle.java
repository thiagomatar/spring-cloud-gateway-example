package br.com.tqi.vss.resources.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vehicle {
    private final String color;
    private final String model;
    private final LocalDate availableIn;
    private final BigDecimal price;

    public Vehicle(String color, String model, LocalDate availableIn, BigDecimal price) {
        this.color = color;
        this.model = model;
        this.availableIn = availableIn;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getAvailableIn() {
        return availableIn;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
