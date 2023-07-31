package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Truck;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class TruckImpl extends VehicleBase implements Truck {

    public static final int WEIGHT_CAP_MIN = 1;
    public static final int WEIGHT_CAP_MAX = 100;
    private static final String WEIGHT_CAP_ERR = format(
            "Weight capacity must be between %d and %d!",
            WEIGHT_CAP_MIN,
            WEIGHT_CAP_MAX);
    public static final int TRUCK_WHEELS_COUNT = 8;
    private int weightCapacity;

    public TruckImpl(String make, String model, int wheelsCount, double price, int weightCapacity) {
        super(make, model, TRUCK_WHEELS_COUNT, price);
    }

    @Override
    public int getWeightCapacity() {
        return weightCapacity;
    }
    private void setWeightCapacity(int weightCapacity){
        validateWeightCapacity(weightCapacity);
        this.weightCapacity=weightCapacity;
    }

    private static void validateWeightCapacity(int weightCapacity){
        ValidationHelpers.validateIntRange(weightCapacity,WEIGHT_CAP_MIN,WEIGHT_CAP_MAX,WEIGHT_CAP_ERR);
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void removeComment(Comment comment) {

    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }
    //TODO
}