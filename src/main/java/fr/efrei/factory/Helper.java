package fr.efrei.factory;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Helper {
    public static String generateId(){ return UUID.randomUUID().toString(); }

    public static boolean isNullOrEmpty(String str){
        return(str==null||str.isEmpty());
    }

    public static boolean isYoungerThan18YearsOld(LocalDate birthDate) { return Period.between(birthDate, LocalDate.now()).getYears() <= 18; }

    public static boolean respectsDateFormat(String s){
        return(s.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    public static boolean respectsEmailFormat(String s){
        return (s.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b"));
        // took on https://stackoverflow.com/questions/8204680/java-regex-email
    }

    public static boolean respectsPhoneNumberFormat(String s){
        return (s.matches("/^[+]?(\\d{1,2})?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$/"));
        // took on https://stackoverflow.com/questions/16699007/regular-expression-to-match-standard-10-digit-phone-number
    }
}
