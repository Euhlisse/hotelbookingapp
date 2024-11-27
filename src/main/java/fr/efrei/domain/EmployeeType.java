package fr.efrei.domain;

import java.util.Arrays;

public enum EmployeeType {
    FRONT_DESK_AGENT,
    DIRECTOR,
    MANAGER,
    STAFF_MEMBER;

    public static boolean contains(EmployeeType employeeType){
        return (Arrays.stream(EmployeeType.values()).anyMatch(val -> val.equals(employeeType)));
    }
}
