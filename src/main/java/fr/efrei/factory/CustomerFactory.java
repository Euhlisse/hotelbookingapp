package fr.efrei.factory;

import fr.efrei.domain.Customer;
import java.time.LocalDate;

public class CustomerFactory {
    public static Customer createCustomer(String firstName, String lastName, String email, String phone, LocalDate birthDate){
        String idNumber = Helper.generateId();
        if (Helper.isNullOrEmpty(idNumber)
            ||Helper.isNullOrEmpty(firstName)
            ||Helper.isNullOrEmpty(lastName)
            ||Helper.isNullOrEmpty(email)
            ||Helper.isNullOrEmpty(phone)
            ||Helper.isNullOrEmpty(String.valueOf(birthDate))){
                return null;
        }

        if (Helper.isYoungerThan18YearsOld(birthDate)){
            return null;
        }

        if (!phone.matches("^\\+?\\d{1,12}$")){
            return null;
        }

        return new Customer.BuilderCustomer()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIdNumber(idNumber)
                .setEmail(email)
                .setPhone(phone)
                .setBirthDate(birthDate)
                .build();
    }
}
