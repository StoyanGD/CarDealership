package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class MotorcycleImpl extends VehicleBase implements Motorcycle {

    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);
    public static final int MOTORCYCLE_WHEELS_COUNT = 2;
    private String category;

    public MotorcycleImpl(String make, String model, int wheelsCount, double price, String category) {
        super(make, model, MOTORCYCLE_WHEELS_COUNT, price);
        setCategory(category);
    }

    @Override
    public String getCategory() {
        return category;
    }
    private void setCategory(String category){
        validateCategory(category);
        this.category=category;
    }

    private static void validateCategory(String category){
        ValidationHelpers.validateIntRange(category.length(),CATEGORY_LEN_MIN,CATEGORY_LEN_MAX,CATEGORY_LEN_ERR);
    }


    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override
    public void addComment(Comment comment) {
//TODO
    }

    @Override
    public void removeComment(Comment comment) {
//TODO
    }
    //TODO String
}