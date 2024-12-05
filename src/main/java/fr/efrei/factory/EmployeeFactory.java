package fr.efrei.factory;

import fr.efrei.domain.Employee;
import fr.efrei.domain.EmployeeType;

import java.time.LocalDate;

public class EmployeeFactory {
    public static Employee createEmployee(String employeeNumber, String firstName, String lastName, LocalDate birthDate, EmployeeType employeeType, String password){
        if (Helper.isNullOrEmpty(employeeNumber)
            ||Helper.isNullOrEmpty(firstName)
            ||Helper.isNullOrEmpty(lastName)
            ||Helper.isNullOrEmpty(String.valueOf(birthDate))
            ||Helper.isNullOrEmpty(String.valueOf(employeeType))
            ||Helper.isNullOrEmpty(password)){
                return null;
        }

        if (!EmployeeType.contains(employeeType)){
            return null;
        }

        if(Helper.isYoungerThan18YearsOld(birthDate)){
            return null;
        }

        return new Employee.BuilderEmployee()
                .setEmployeeNumber(employeeNumber)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setEmployeeType(employeeType)
                .setPassword(password)
                .build();
    }
}
