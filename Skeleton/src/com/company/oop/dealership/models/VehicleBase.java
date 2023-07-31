package com.company.oop.dealership.models;

import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

abstract class VehicleBase {
    public static final int MAKE_MIN_LENGTH = 2;
    public static final int MAKE_MAX_LENGTH = 15;
    public static final String
            MAKE_LENGTH_ERROR_MESSAGE = String.format("Make must be between %d and %d characters long!",
            MAKE_MIN_LENGTH,
            MAKE_MAX_LENGTH);
    public static final int MODEL_MIN_LENGTH = 1;
    public static final int MODEL_MAX_LENGTH = 15;
    public static final String MODEL_LENGTH_ERROR_MESSAGE = String.format("Model must be between %d and %d characters long!",
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

    public VehicleBase(String make, String model, int wheelsCount, double price, VehicleType vehicleType) {
        setMake(make);
        setModel(model);
        setWheelsCount(wheelsCount);
        setPrice(price);
        this.vehicleType=vehicleType;
    }

    public String getMake() {
        return make;
    }

    private void setMake(String make) {
        validateMake(make);
        this.make = make;
    }

    private static void validateMake(String make){
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

    private static void validateModel(String model){
        ValidationHelpers.validateIntRange(model.length(),
                MODEL_MIN_LENGTH,
                MODEL_MAX_LENGTH,
                MODEL_LENGTH_ERROR_MESSAGE);
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        validatePriceInRange(price);
        this.price = price;
    }

    private static void validatePriceInRange(double price){
        ValidationHelpers.validateDecimalRange(price,
                MIN_PRICE,
                MAX_PRICE,
                PRICE_IN_RANGE_ERROR);
    }

    public int getWheelsCount() {
        return wheelsCount;
    }

    private void setWheelsCount(int wheelsCount) {
        validateWheelsCount(wheelsCount);
        this.wheelsCount = wheelsCount;
    }

     protected void validateWheelsCount(int WheelsCount){};
}
