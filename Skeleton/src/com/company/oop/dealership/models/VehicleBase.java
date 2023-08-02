package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

abstract class VehicleBase implements Vehicle {
    public static final int MAKE_MIN_LENGTH = 2;
    public static final int MAKE_MAX_LENGTH = 15;
    public static final String
            MAKE_LENGTH_ERROR_MESSAGE = String.format("Make must be between %d and %d characters long!",
            MAKE_MIN_LENGTH,
            MAKE_MAX_LENGTH);
    public static final int MODEL_MIN_LENGTH = 1;
    public static final int MODEL_MAX_LENGTH = 15;
    public static final String MODEL_LENGTH_ERROR_MESSAGE =
            String.format("Model must be between %d and %d characters long!",
            MODEL_MIN_LENGTH,
            MODEL_MAX_LENGTH);
    public static final double MIN_PRICE = 0.0;
    public static final double MAX_PRICE = 1000000.0;
    public static final String PRICE_IN_RANGE_ERROR = String.format("Price must be between %.1f and %.1f!",
            MIN_PRICE,
            MAX_PRICE);
    private String make;
    private String model;
    private int wheelsCount;
    private double price;
    private VehicleType vehicleType;
    private List<Comment> comments;

    public VehicleBase(String make, String model, int wheelsCount, double price) {
        setMake(make);
        setModel(model);
        this.wheelsCount = wheelsCount;
        setPrice(price);
        comments = new ArrayList<>();
    }

    public String getMake() {

        return make;
    }

    private void setMake(String make) {
        validateMake(make);
        this.make = make;
    }

    private static void validateMake(String make) {
        ValidationHelpers.validateIntRange(make.length(),
                MAKE_MIN_LENGTH,
                MAKE_MAX_LENGTH,
                MAKE_LENGTH_ERROR_MESSAGE);
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        validateModel(model);
        this.model = model;
    }

    private static void validateModel(String model) {
        ValidationHelpers.validateIntRange(model.length(),
                MODEL_MIN_LENGTH,
                MODEL_MAX_LENGTH,
                MODEL_LENGTH_ERROR_MESSAGE);
    }

    @Override
    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        validatePriceInRange(price);
        this.price = price;
    }

    private static void validatePriceInRange(double price) {
        ValidationHelpers.validateDecimalRange(price,
                MIN_PRICE,
                MAX_PRICE,
                PRICE_IN_RANGE_ERROR);
    }

    @Override
    public int getWheels() {
        return wheelsCount;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public String toString() {
        return String.format(
                "%s:\n" +
                        "Make: %s\n" +
                        "Model: %s\n" +
                        "Wheels: %d\n" +
                        "Price: $%s",
                getType(),
                getMake(),
                getModel(),
                getWheels(),
                FormattingHelpers.removeTrailingZerosFromDouble(getPrice()));
    }
}
