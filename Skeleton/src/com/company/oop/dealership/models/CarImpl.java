package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class CarImpl extends VehicleBase implements Car {

    public static final int CAR_SEATS_MIN = 1;
    public static final int CAR_SEATS_MAX = 10;
    private static final String CAR_SEATS_ERR = format(
            "Seats must be between %d and %d!",
            CAR_SEATS_MIN,
            CAR_SEATS_MAX);
    public static final int CAR_WHEELS_COUNT = 4;
    private int seats;


    public CarImpl(String make, String model, double price, int seats) {
        super(make, model, CAR_WHEELS_COUNT, price);
        setSeats(seats);
    }

    @Override
    public int getSeats() {
        return seats;
    }

    private void setSeats(int seats){
        validateSeatsNumber(seats);
        this.seats=seats;
    }

    private static void validateSeatsNumber(int seats){
        ValidationHelpers.validateIntRange(seats,CAR_SEATS_MIN,CAR_SEATS_MAX,CAR_SEATS_ERR);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    public void addComment(Comment comment) {
        super.addComment(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        super.removeComment(comment);
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                "Seats: %d",super.toString(),getSeats());
    }
}