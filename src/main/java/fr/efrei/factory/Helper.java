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

}
