package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowUsersCommand extends BaseCommand {
    private static final String NOT_ADMIN_ERR = "You are not an admin!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showUsersList();
    }

    private String showUsersList() {
        User user = getVehicleDealershipRepository().getLoggedInUser();
        if(!user.isAdmin()) {
            throw new IllegalArgumentException(NOT_ADMIN_ERR);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--USERS--\n");
        int i = 0;
        for(User users : getVehicleDealershipRepository().getUsers()) {
            i++;
            stringBuilder.append(String.format("%d. %s", i, users.toString()));

            if(i < getVehicleDealershipRepository().getUsers().size()) {
                stringBuilder.append(String.format("\n"));
            }
        }
        return stringBuilder.toString();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}