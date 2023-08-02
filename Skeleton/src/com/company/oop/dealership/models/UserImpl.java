package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class UserImpl implements User {

    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
    private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
    private static final String USERNAME_LEN_ERR = format(
            "Username must be between %d and %d characters long!",
            USERNAME_LEN_MIN,
            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 30;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
    private static final String PASSWORD_LEN_ERR = format(
            "Password must be between %s and %s characters long!",
            PASSWORD_LEN_MIN,
            PASSWORD_LEN_MAX);

    public static final int LASTNAME_LEN_MIN = 2;
    public static final int LASTNAME_LEN_MAX = 20;
    private static final String LASTNAME_LEN_ERR = format(
            "Lastname must be between %s and %s characters long!",
            LASTNAME_LEN_MIN,
            LASTNAME_LEN_MAX);

    public static final int FIRSTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MAX = 20;
    private static final String FIRSTNAME_LEN_ERR = format(
            "Firstname must be between %s and %s characters long!",
            FIRSTNAME_LEN_MIN,
            FIRSTNAME_LEN_MAX);
    private final static String NOT_AN_VIP_USER_VEHICLES_ADD =
            "You are not VIP and cannot add more than %d vehicles!";
    private final static String ADMIN_CANNOT_ADD_VEHICLES =
            "You are an admin and therefore cannot add vehicles!";
    private static final String YOU_ARE_NOT_THE_AUTHOR =
            "You are not the author of the comment you are trying to remove!";
    private final static String USER_TO_STRING = "Username: %s, FullName: %s %s, Role: %s";
    private final static String NO_VEHICLES_HEADER = "--NO VEHICLES--";
    private final static String USER_HEADER = "--USER %s--";
    private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;
    public static final String NO_COMMENTS = "--NO COMMENTS--";
    public static final String COMMENTS_HEADER = "--COMMENTS--";
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole;
    private List<Vehicle> vehicles = new ArrayList<>();

    public UserImpl(String username, String firstName, String lastName, String password, UserRole role) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        this.userRole = role;
        vehicles = new ArrayList<>();
    }

//TODO

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return username.equals(user.username) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && userRole == user.userRole;
    }

    @Override
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        validateUsername(username);
        this.username = username;
    }

    private static void validateUsername(String username) {
        ValidationHelpers.validateIntRange(username.length(), USERNAME_LEN_MIN, USERNAME_LEN_MAX, USERNAME_LEN_ERR);
        ValidationHelpers.validatePattern(username, USERNAME_REGEX_PATTERN, USERNAME_PATTERN_ERR);
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    private static void validateFirstName(String firstName) {
        ValidationHelpers.validateIntRange(firstName.length(), FIRSTNAME_LEN_MIN, FIRSTNAME_LEN_MAX, FIRSTNAME_LEN_ERR);
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    private static void validateLastName(String lastName) {
        ValidationHelpers.validateIntRange(lastName.length(), LASTNAME_LEN_MIN, LASTNAME_LEN_MAX, LASTNAME_LEN_ERR);
    }

    @Override
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public static void validatePassword(String password) {
        ValidationHelpers.validateIntRange(password.length(), PASSWORD_LEN_MIN, PASSWORD_LEN_MAX, PASSWORD_LEN_ERR);
        ValidationHelpers.validatePattern(password, PASSWORD_REGEX_PATTERN, PASSWORD_PATTERN_ERR);
    }

    @Override
    public UserRole getRole() {
        return userRole;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (getRole().equals(UserRole.ADMIN)) {
            throw new IllegalArgumentException(ADMIN_CANNOT_ADD_VEHICLES);
        }
        if (getRole().equals(UserRole.NORMAL)) {
            if (vehicles.size() < NORMAL_ROLE_VEHICLE_LIMIT) {
                vehicles.add(vehicle);

            } else throw new IllegalArgumentException
                    (String.format(NOT_AN_VIP_USER_VEHICLES_ADD, NORMAL_ROLE_VEHICLE_LIMIT));
        }
        if (getRole().equals(UserRole.VIP)) {
            vehicles.add(vehicle);
        }
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public void addComment(Comment commentToAdd, Vehicle vehicleToAddComment) {
        vehicleToAddComment.addComment(commentToAdd);
    }

    @Override
    public void removeComment(Comment commentToRemove, Vehicle vehicleToRemoveComment) {
        if (!commentToRemove.getAuthor().equals(getUsername())) {
            throw new IllegalArgumentException(YOU_ARE_NOT_THE_AUTHOR);
        } else {
            vehicleToRemoveComment.removeComment(commentToRemove);
        }
    }

    @Override
    public String printVehicles() {
        int count = 1;
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(USER_HEADER + "\n", username));
        if (vehicles.isEmpty()) {
            builder.append(NO_VEHICLES_HEADER);
            return builder.toString();
        }
        for (Vehicle vehicle : vehicles) {
            builder.append(String.format("%d. ", count));
            builder.append(vehicle + "\n");
            if (vehicle.getComments().isEmpty())
                if (vehicles.indexOf(vehicle) == vehicles.size() - 1) {
                    builder.append(NO_COMMENTS);
                } else {
                    builder.append(NO_COMMENTS + "\n");
                }

            else {
                builder.append(COMMENTS_HEADER+"\n");
                    for(Comment comment: vehicle.getComments()) {
                        builder.append(comment);
                    }
                builder.append(COMMENTS_HEADER+"\n");
            }
                count++;
        }
        count = 0;
        return builder.toString();
    }


    @Override
    public boolean isAdmin() {
        if (getRole().equals(UserRole.ADMIN))
            return true;
        else return false;
    }

    @Override
    public String toString() {
        return String.format(USER_TO_STRING, getUsername(), getFirstName(), getLastName(), getRole());
    }
}