package fr.efrei.factory;

import fr.efrei.domain.Employee;
import fr.efrei.domain.EmployeeType;

import java.time.LocalDate;
import java.util.Set;

public class EmployeeFactory {
    public static Employee createEmployee(String firstName, String lastName, LocalDate birthDate, EmployeeType jobType,String password){
        String employeeId = Helper.generateRandomId();
        if (Helper.isNullOrEmpty(employeeId)
                ||Helper.isNullOrEmpty(firstName)
                ||Helper.isNullOrEmpty(lastName)
                ||Helper.isNullOrEmpty(String.valueOf(birthDate))
                ||Helper.isNullOrEmpty(String.valueOf(jobType))
                ||Helper.isNullOrEmpty(password)){
            return null;
        }

        if (!EmployeeType.contains(jobType)){
            System.out.println("employee type is invalid");
            return null;
        }

        if(Helper.isYoungerThan18YearsOld(birthDate)){
            System.out.println("Employee should be older than 18 years old");
            return null;
        }

        return new Employee.BuilderEmployee()
                .setEmployeeNumber(employeeId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setEmployeeType(jobType)
                .setPassword(password)
                .build();
    }
    public static Employee createEmployee(String employeeId,String firstName, String lastName, LocalDate birthDate, EmployeeType jobType,String password){
        if (Helper.isNullOrEmpty(employeeId)
                ||Helper.isNullOrEmpty(firstName)
                ||Helper.isNullOrEmpty(lastName)
                ||Helper.isNullOrEmpty(String.valueOf(birthDate))
                ||Helper.isNullOrEmpty(String.valueOf(jobType))
                ||Helper.isNullOrEmpty(password)){
            return null;
        }

        if (!EmployeeType.contains(jobType)){
            System.out.println("employee type is invalid");
            return null;
        }

        if(Helper.isYoungerThan18YearsOld(birthDate)){
            System.out.println("Employee should be older than 18 years old");
            return null;
        }

        return new Employee.BuilderEmployee()
                .setEmployeeNumber(employeeId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setEmployeeType(jobType)
                .setPassword(password)
                .build();
    }
}
