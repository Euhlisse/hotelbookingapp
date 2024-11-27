package fr.efrei.factory;

import fr.efrei.domain.Employee;
import fr.efrei.domain.EmployeeType;

import java.time.LocalDate;
import java.util.Set;

public class EmployeeFactory {
    public static Employee createEmployee(String employeeNumber, String firstName, String lastName, LocalDate birthDate, EmployeeType jobType){
        if (Helper.isNullOrEmpty(employeeNumber)
            ||Helper.isNullOrEmpty(firstName)
            ||Helper.isNullOrEmpty(lastName)
            ||Helper.isNullOrEmpty(String.valueOf(birthDate))){
                return null;
        }

        if (!EmployeeType.contains(jobType)){
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
                .setJobType(jobType)
                .build();
    }
}
