package fr.efrei.factory;

import fr.efrei.domain.Employee;

import java.time.LocalDate;
import java.util.Set;

public class EmployeeFactory {
    public static Employee createEmployee(String firstName, String lastName, LocalDate birthDate, String jobType){
        if (Helper.isNullOrEmpty(firstName)
            ||Helper.isNullOrEmpty(lastName)
            ||Helper.isNullOrEmpty(String.valueOf(birthDate))
            ||Helper.isNullOrEmpty(jobType)){
                return null;
        }
        Set<String> JobTypes = Set.of("Director","Manager","Front desk agent", "Staff member");
        if (!JobTypes.contains(jobType)){
            return null;
        }

        if(Helper.isYoungerThan18YearsOld(birthDate)){
            return null;
        }

        return new Employee.BuilderEmployee()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(birthDate)
                .setJobType(jobType)
                .build();
    }
}
