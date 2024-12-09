package fr.efrei.factory;

import fr.efrei.domain.Customer;
import fr.efrei.domain.EmployeeType;
import fr.efrei.domain.Room;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.UUID;

public class Helper {

    public static String generateBookingId(Room room, LocalDate dateArrival, LocalDate dateDeparture, Customer customer) {
        return room.getRoomNumber() + "_" + dateArrival.toString() + "_" + dateDeparture.toString() + "_" + customer.getFirstName().charAt(0) + "_" + customer.getLastName().charAt(0);
    }

    public static String generateRandomId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomId = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            randomId.append(characters.charAt(index));
        }

        return randomId.toString();
    }


    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static boolean isYoungerThan18YearsOld(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() <= 18;
    }

    public static boolean respectsDateFormat(String s) {
        return (s.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    public static boolean respectsEmailFormat(String s) {
        return (s.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b"));
    }

    public static boolean respectsPhoneNumberFormat(String s) {
        return (s.matches("/^[+]?(\\d{1,2})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$/"));
    }
}

